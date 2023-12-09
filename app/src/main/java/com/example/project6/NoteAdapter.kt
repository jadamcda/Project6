package com.example.project6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val noteList:ArrayList<Note>)
    : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    var onButtonClick : ((Note) -> Unit)? = null
    var onNoteClick : ((Note) -> Unit)? = null
    var thisNoteList = noteList

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageButton : ImageButton = itemView.findViewById(R.id.imageButton)
        val title : TextView = itemView.findViewById(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_view, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = thisNoteList[position]
        holder.title.text = note.title

        holder.imageButton.setOnClickListener{
            onButtonClick?.invoke(note)
        }

        holder.title.setOnClickListener{
            onNoteClick?.invoke(note)
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }










}