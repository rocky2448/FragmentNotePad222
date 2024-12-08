package com.example.fragmentnotepad

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Date


class NotePadFragment : Fragment() {

    private val notes: MutableList<Note> = mutableListOf()
    private var count: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("FragmentNotePad", "OnCreateView")
        return inflater.inflate(R.layout.fragment_note_pad, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val generateCountTV: TextView = view.findViewById(R.id.generateCountTV)
        val textNoteET: EditText = view.findViewById(R.id.textNoteET)
        val addBTN: Button = view.findViewById(R.id.addBTN)
        val recyclerViewRV: RecyclerView = view.findViewById(R.id.recyclerViewRV)

        generateCountTV.text = "№ $count"
        recyclerViewRV.layoutManager = LinearLayoutManager(context)
        val adapter = CustomAdapter(notes)
        recyclerViewRV.adapter = adapter
        recyclerViewRV.setHasFixedSize(true)

        addBTN.setOnClickListener {
            Log.d("Add Button", "OnViewCreated")

            if (textNoteET.text.isEmpty()) {
                return@setOnClickListener
            }
            val textNote = textNoteET.text.toString()
            val dateCreate = Date().toString()
            val checkBoxCondition = false
            val note = Note(count, textNote, dateCreate, checkBoxCondition)
            notes.add(note)
            adapter.notifyDataSetChanged()
            count = ((notes[notes.size - 1].count) + 1)
            generateCountTV.text = "№ $count"
            textNoteET.text.clear()
        }
    }
}