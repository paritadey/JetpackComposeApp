package com.parita.jetpackcomposeapp.repository

import androidx.lifecycle.LiveData
import com.google.gson.JsonObject
import com.parita.jetpackcomposeapp.api.RetrofitApi
import com.parita.jetpackcomposeapp.data.NotesData
import com.parita.jetpackcomposeapp.room.NotesDatabaseDao
import com.parita.jetpackcomposeapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityScoped
class JetpackRepository @Inject constructor(private val api: RetrofitApi, private val notesDatabaseDao: NotesDatabaseDao) {
    suspend fun getMusicTracks(
        term: String,
        locale:String,
        offset: Int,
        limit: Int
    ): Resource<JsonObject> {
        val response = try {
            api.getTrackHits(term, locale, offset, limit)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }

    val fetchAllNotes: Flow<List<NotesData>> = notesDatabaseDao.getAllNotes()

    suspend fun addNote(notesData: NotesData) {
        notesDatabaseDao.insert(notesData)
    }

    suspend fun deleteNote(notesData: NotesData) {
        notesDatabaseDao.delete(notesData)
    }

    suspend fun updateNote(note_description: String, note_last_modified: String, noteId: String) {
        notesDatabaseDao.updateLastModifiedDescNDate(note_description, note_last_modified, noteId)
    }

    suspend fun deleteNoteById(noteId: String) {
        notesDatabaseDao.deleteNote(noteId)
    }

}