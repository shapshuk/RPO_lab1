package com.example.lab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.lab1.databinding.ActivityAddEditNoteBinding
import com.example.lab1.mainactivity.MainActivity
import com.example.lab1.room.NoteModel
import com.example.lab1.mainactivity.NoteViewModel

class AddEditNoteActivity : AppCompatActivity() {

    private val binding : ActivityAddEditNoteBinding by lazy {
        ActivityAddEditNoteBinding.inflate(layoutInflater)
    }

    lateinit var viewModel: NoteViewModel
    var noteId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]

        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteText = intent.getStringExtra("noteText")
            noteId = intent.getIntExtra("noteId", -1)

            binding.button.text = "Update note"
            binding.noteTitle.setText(noteTitle)
            binding.text.setText(noteText)
        } else {
            binding.button.text = "Save note"
        }

        binding.button.setOnClickListener {
            val noteTitle = binding.noteTitle.text.toString()
            val noteText = binding.text.text.toString()

            if (noteTitle.isNotEmpty() && noteText.isNotEmpty()) {
                if (noteType.equals("Edit")) {

                    val updateNote = NoteModel(noteTitle, noteText)
                    updateNote.id = noteId
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this, "Note updated...", Toast.LENGTH_LONG).show()
                }
                else {
                    val newNote = NoteModel(noteTitle, noteText)
                    viewModel.addNote(newNote)
                    Toast.makeText(this, "Note added...", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}