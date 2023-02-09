package com.example.lab1.mainactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1.AddEditNoteActivity
import com.example.lab1.databinding.ActivityMainBinding
import com.example.lab1.room.NoteModel

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {

    lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: NoteViewModel

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(this)

        val recyclerViewAdapter = RecyclerViewAdapter(this, this)

        recyclerView.adapter = recyclerViewAdapter

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]

        viewModel.allNotes.observe(this) {
            recyclerViewAdapter.updateList(it)
        }

        binding.addButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        binding.searchButton.setOnClickListener {
            viewModel.updateList(binding.searchField.text.toString())
        }
    }

    override fun onDeleteIconClick(note: NoteModel) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.title} was deleted", Toast.LENGTH_LONG).show()
    }

    override fun onNoteClick(note: NoteModel) {
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.title)
        intent.putExtra("noteText", note.text)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
    }
}