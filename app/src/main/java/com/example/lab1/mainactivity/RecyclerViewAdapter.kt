package com.example.lab1.mainactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1.R
import com.example.lab1.databinding.NoteItemBinding
import com.example.lab1.room.NoteModel

class RecyclerViewAdapter(
    val noteClickInterface: NoteClickInterface,
    val noteClickDeleteInterface: NoteClickDeleteInterface,
    ) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>()
{

    private val allNotes = ArrayList<NoteModel>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = NoteItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.title.text = allNotes[position].title
        holder.binding.text.text = allNotes[position].text

        holder.binding.deleteButton.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes[position])
        }

        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes[position])
        }
    }

    override fun getItemCount() = allNotes.size

    fun updateList(newList: List<NoteModel>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface NoteClickDeleteInterface {
    fun onDeleteIconClick(note: NoteModel)
}

interface NoteClickInterface {
    fun onNoteClick(note: NoteModel)
}