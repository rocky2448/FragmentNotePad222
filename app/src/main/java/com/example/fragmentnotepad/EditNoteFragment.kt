package com.example.fragmentnotepad

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction

class EditNoteFragment : Fragment(), OnFragmentDataListener {

    private lateinit var onFragmentDataListener: OnFragmentDataListener
    private var textNote: String? = null
    private var position: Int? = 0
    private lateinit var editNoteET: EditText
    private lateinit var editBTN: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("FragmentEditNote", "OnCreateView")
        onFragmentDataListener = requireActivity() as OnFragmentDataListener
        val view = inflater.inflate(R.layout.fragment_edit_note, container, false)
        textNote = arguments?.getString("text")
        position = arguments?.getInt("position")
        editNoteET = view.findViewById(R.id.editNoteET)
        editBTN = view.findViewById(R.id.editBTN)
        editNoteET.setText(textNote)
        editBTN.setOnClickListener {
            if (editNoteET.text.isEmpty()) {
                return@setOnClickListener
            }
            val value = editNoteET.text
            onData(value.toString())
        }
        return view
    }

    override fun onData(data: String?) {
        val bundle = Bundle()
        bundle.putString("newText", data)

        val transaction = this.fragmentManager?.beginTransaction()
        val notePadFragment = NotePadFragment()
        notePadFragment.arguments = bundle

        transaction?.replace(R.id.containerID, notePadFragment)
        transaction?.addToBackStack(null)
        transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction?.commit()
    }
}