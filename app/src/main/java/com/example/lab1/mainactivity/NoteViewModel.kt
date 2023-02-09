package com.example.lab1.mainactivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lab1.room.AppDatabase
import com.example.lab1.room.NoteModel
import com.example.lab1.room.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val _allNotes = MutableLiveData<List<NoteModel>>()
    val allNotes = _allNotes

    private val repository: NoteRepository

    init {
        val dao = AppDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        _allNotes.value = repository.allNotes
    }

    fun updateList(searchRequest : String) {
        if (searchRequest == "") {
            _allNotes.value = repository.allNotes
        } else {
            _allNotes.value = repository.searchNotes(searchRequest)
        }
    }

    fun deleteNote(note: NoteModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun updateNote(note: NoteModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }

    fun addNote(note: NoteModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}