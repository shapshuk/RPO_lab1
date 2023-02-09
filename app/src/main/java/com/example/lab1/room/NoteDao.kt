package com.example.lab1.room

import androidx.room.*

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getAll(): List<NoteModel>

    @Query("SELECT * FROM notes WHERE title LIKE :title")
    fun findByTitle(title: String): List<NoteModel>

    @Update
    suspend fun update(note: NoteModel)

    @Insert
    suspend fun insert(note: NoteModel)

    @Delete
    suspend fun delete(note: NoteModel)
}