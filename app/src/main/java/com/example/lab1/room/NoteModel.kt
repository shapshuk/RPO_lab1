package com.example.lab1.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class NoteModel(
    val title: String,
    val text: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
