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
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Date

class NotePadFragment : Fragment() {

    private lateinit var adapter: CustomAdapter
    private lateinit var onFragmentDataListener: OnFragmentDataListener
    private lateinit var generateCountTV: TextView
    private lateinit var textNoteET: EditText
    private lateinit var addBTN: Button
    private lateinit var recyclerViewRV: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("FragmentNotePad", "OnCreateView")
        onFragmentDataListener = requireActivity() as OnFragmentDataListener
        val view = inflater.inflate(R.layout.fragment_note_pad, container, false)

        generateCountTV = view.findViewById(R.id.generateCountTV)
        textNoteET = view.findViewById(R.id.textNoteET)
        addBTN = view.findViewById(R.id.addBTN)
        recyclerViewRV = view.findViewById(R.id.recyclerViewRV)

        generateCountTV.text = "№ $count"

        adapter = CustomAdapter(notes)
        recyclerViewRV.adapter = adapter

        recyclerViewRV.setHasFixedSize(true)
        recyclerViewRV.layoutManager = LinearLayoutManager(context)
        adapter.setOnNoteClickListener(object :
            CustomAdapter.OnNoteClickListener {
                override fun OnNoteClick(note: Note, position: Int) {
                    Toast.makeText(context, "Позиция элемента $position", Toast.LENGTH_SHORT).show()
                    onFragmentDataListener.onData(notes[position].textNote, position)
                }
            }
        )

        addBTN.setOnClickListener {
            Log.d("Add Button", "OnPressed")

            if (textNoteET.text.isEmpty()) {
                return@setOnClickListener
            }
            val textNote = textNoteET.text.toString()
            val dateCreate = Date().toString()
            val checkBoxCondition = false
            val note = Note(count, textNote, dateCreate, checkBoxCondition)
            notes.add(note)
            adapter.notifyDataSetChanged()
            Toast.makeText(context, "Заметка добавлена", Toast.LENGTH_SHORT).show()
            count = ((notes[notes.size - 1].count) + 1)
            generateCountTV.text = "№ $count"
            textNoteET.text.clear()
        }
        return view
    }

    override fun onResume() {
        Log.d("OnResume", "Activate")
        super.onResume()
        val text = arguments?.getString("newText")
        if (text != null) {
            val position = arguments?.getInt("position")
            swap(notes, position!!, text)
            adapter.notifyDataSetChanged()
        }
    }

    private fun swap(notes: MutableList<Note>, position: Int, textNote: String) {
        Toast.makeText(context, "Позиция элемента равна $position", Toast.LENGTH_SHORT).show()
        if (position in notes.indices) {
            notes[position].textNote = textNote
        }
    }

//    fun swap(notes: MutableList<Note>, position: Int, textNote: String) {
//        notes[position].textNote = textNote
//    }
}