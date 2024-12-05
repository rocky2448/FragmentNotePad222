package com.example.fragmentnotepad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(val notes: MutableList<Note>) :
    RecyclerView.Adapter<CustomAdapter.NoteViewHolder>() {

        class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val countTV: TextView = itemView.findViewById(R.id.countTV)
        val textNoteTV: TextView = itemView.findViewById(R.id.textNoteTV)
        var dateCreateTV: TextView = itemView.findViewById(R.id.dateCreateTV)
        val checkBoxConditionCB: CheckBox = itemView.findViewById(R.id.checkBoxNoteCB)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.countTV.text = note.count.toString()
        holder.textNoteTV.text = note.textNote
        holder.dateCreateTV.text = note.dateCreate
        holder.checkBoxConditionCB.isChecked = note.checkBoxCondition
    }
}