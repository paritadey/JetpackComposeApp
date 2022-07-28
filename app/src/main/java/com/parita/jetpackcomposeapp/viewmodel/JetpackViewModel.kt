package com.parita.jetpackcomposeapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.parita.jetpackcomposeapp.data.NotesData
import com.parita.jetpackcomposeapp.data.Track
import com.parita.jetpackcomposeapp.repository.JetpackRepository
import com.parita.jetpackcomposeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JetpackViewModel @Inject constructor(private val repository: JetpackRepository) :
    ViewModel() {
    var isLoading = mutableStateOf(true)
    var _hitList: MutableLiveData<ArrayList<Track>> = MutableLiveData()
    var hitList: LiveData<ArrayList<Track>> = _hitList
    val query = mutableStateOf("")
    var startSearch = mutableStateOf(false)
    var noteQuery = mutableStateOf("")
    var creationTime = mutableStateOf("")
    var title = mutableStateOf("")
    var description = mutableStateOf("")
    var category = mutableStateOf("")
    var dueDate = mutableStateOf("")
    var lastNoteId = mutableStateOf(0)
    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()


    suspend fun getMusicData(searchName: String): Resource<JsonObject> {
        var hits: ArrayList<Track> = ArrayList()
        val result = repository.getMusicTracks(searchName, "en-US", 0, 5)
        if (result is Resource.Success) {
            isLoading.value = false
            for (i in result.data?.asJsonObject?.keySet()!!) {
                if (i.equals("tracks")) {
                    for (j in result.data.asJsonObject.getAsJsonObject(i).asJsonObject.keySet()) {
                        result.data.asJsonObject.getAsJsonObject(i)
                            .getAsJsonArray(j).asJsonArray.forEach {
                                for (k in it.asJsonObject.keySet()) {
                                    if (k.equals("track")) {
                                        val data = Gson().fromJson(
                                            it.asJsonObject.getAsJsonObject(k),
                                            Track::class.java
                                        )
                                        hits.add(data)
                                    }
                                }
                            }
                    }
                }
            }
            _hitList.value = hits
        } else if (result is Resource.Error) {
            isLoading.value = false
            _hitList.value = hits
        }
        return result
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
    }

    fun newSearch(query: String) {
        startSearch.value = true
        viewModelScope.launch {
            getMusicData(query)
            viewModelScope.cancel()
            startSearch.value = false
        }
    }

    fun onCreationTime(creationTime: String) {
        this.creationTime.value = creationTime
    }

    fun lastNoteData(lastNoteId: Int) {
        this.lastNoteId.value = lastNoteId
    }

    fun createNoteId(lastCount: Int): Int {
        var count = 1
        if (lastCount > 0) return count + lastCount
        else return 100
    }

    fun onTitleChanged(title: String) {
        this.title.value = title
    }

    fun onDescriptionChanged(description: String) {
        this.description.value = description
    }

    fun onCategoryChanged(category: String) {
        this.category.value = category
    }

    fun onDueDateChanged(dueDate: String) {
        this.dueDate.value = dueDate
    }

    private fun insertNoteData(notesData: NotesData) {
        viewModelScope.launch {
            repository.addNote(notesData)
        }
    }

    val noteList: Flow<List<NotesData>> = repository.fetchAllNotes

    fun onOpenDialogClicked() {
        _showDialog.value = true
    }

    fun onDialogConfirm() {
        _showDialog.value = false
        var notesData = NotesData(
            if (lastNoteId.value == null || lastNoteId.value == 0)  createNoteId(0)
            else  createNoteId(lastNoteId.value),
            title.value,
            description.value,
            creationTime.value,
            creationTime.value,
            category.value,
            dueDate.value
        )
        insertNoteData(notesData)
    }

    fun onDialogDismiss() {
        _showDialog.value = false
    }
}