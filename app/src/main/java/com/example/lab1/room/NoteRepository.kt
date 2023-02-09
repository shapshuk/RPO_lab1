package com.example.lab1.room

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: List<NoteModel> = noteDao.getAll()

    fun searchNotes(title: String) : List<NoteModel> {
        return noteDao.findByTitle(title)
    }

    suspend fun insert(note: NoteModel) {
        noteDao.insert(note)
    }

    suspend fun delete(note: NoteModel) {
        noteDao.delete(note)
    }

    suspend fun update(note: NoteModel) {
        noteDao.update(note)
    }
}