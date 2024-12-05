package com.example.fragmentnotepad

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var toolbarMain: Toolbar
    private lateinit var recyclerViewRV: RecyclerView
    private lateinit var generateCountTV: TextView
    private lateinit var textNoteET: EditText
    private lateinit var addBTN: Button
    private val notes: MutableList<Note> = mutableListOf()
    private var count: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarMain = findViewById(R.id.toolbarMain)
        recyclerViewRV = findViewById(R.id.recyclerViewRV)
        generateCountTV = findViewById(R.id.generateCountTV)
        textNoteET = findViewById(R.id.textNoteET)
        addBTN = findViewById(R.id.addBTN)

        setSupportActionBar(toolbarMain)
        title = "Мои заметки"
        toolbarMain.subtitle = "by Rocky"
        toolbarMain.setLogo(R.drawable.ic_note_pad)

        generateCountTV.text = "№ $count"
        recyclerViewRV.layoutManager = LinearLayoutManager(this)
        val adapter = CustomAdapter(notes)
        recyclerViewRV.adapter = adapter
        recyclerViewRV.setHasFixedSize(true)

        addBTN.setOnClickListener {
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenuMain -> finishAffinity()
        }
        return super.onOptionsItemSelected(item)
    }
}