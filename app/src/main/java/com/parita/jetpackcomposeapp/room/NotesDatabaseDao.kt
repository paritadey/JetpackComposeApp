package com.parita.jetpackcomposeapp.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.parita.jetpackcomposeapp.data.NotesData
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Dao
interface NotesDatabaseDao {
    @Insert
    suspend fun insert(notesData: NotesData)

    @Query("UPDATE notes_list SET note_description= :note_description, note_last_modified = :note_last_modified WHERE noteId = :noteId")
    suspend fun updateLastModifiedDescNDate(
        note_description: String,
        note_last_modified: String,
        noteId: String
    )

    @Delete
    suspend fun delete(notesData: NotesData)

    @Query("Select * from notes_list")
    fun getAllNotes(): Flow<List<NotesData>>

    @Query("Delete from notes_list where noteId = :noteId")
    suspend fun deleteNote(noteId: Int)

/*
    @Query("Select * from notes_list where noteId=:noteId")
    suspend fun fetchNoteById(noteId: String)
*/

    @Query("Update notes_list SET note_due_date=:note_due_date where noteId=:noteId")
    suspend fun updateDueDate(note_due_date: String, noteId: String)

    @Query("Delete from notes_list")
    suspend fun deleteAllNotes()
}