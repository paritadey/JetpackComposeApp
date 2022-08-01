package com.parita.jetpackcomposeapp.room

import androidx.lifecycle.LiveData
import com.parita.jetpackcomposeapp.data.NotesData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepository/* @Inject constructor*/(private val notesDatabaseDao: NotesDatabaseDao) {

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

    suspend fun deleteNoteById(noteId: Int) {
        notesDatabaseDao.deleteNote(noteId)
    }
}