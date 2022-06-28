package com.parita.jetpackcomposeapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.parita.jetpackcomposeapp.data.Track
import com.parita.jetpackcomposeapp.repository.JetpackRepository
import com.parita.jetpackcomposeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class JetpackViewModel @Inject constructor(private val repository: JetpackRepository) :
    ViewModel() {
    var isLoading = mutableStateOf(true)
    var _hitList: MutableLiveData<ArrayList<Track>> = MutableLiveData()
    var hitList: LiveData<ArrayList<Track>> = _hitList

    suspend fun getMusicData(): Resource<JsonObject> {
        var hits: ArrayList<Track> = ArrayList()
        val result = repository.getMusicTracks("Kiss the rain", "en-US", 0, 5)
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
}