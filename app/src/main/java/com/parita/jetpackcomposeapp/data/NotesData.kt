package com.parita.jetpackcomposeapp.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "notes_list")
data class NotesData(
    @PrimaryKey(autoGenerate = false)
    var noteId: Int,
    @ColumnInfo(name = "note_title")
    var noteTitle: String,
    @ColumnInfo(name = "note_description")
    var noteDescription: String,
    @ColumnInfo(name = "note_created_at")
    var noteCreationTime: String,
    @ColumnInfo(name = "note_last_modified")
    var noteLastModified: String,
    @ColumnInfo(name = "note_category")
    var noteCategory: String,
    @ColumnInfo(name = "note_due_date")
    var noteDueDate: String
) : Parcelable {
    constructor() : this(1, "", "", "", "", "", "")
}
