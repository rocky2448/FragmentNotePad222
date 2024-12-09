package com.example.fragmentnotepad

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(), OnFragmentDataListener {

    private lateinit var toolbarMain: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarMain = findViewById(R.id.toolbarMain)

        setSupportActionBar(toolbarMain)
        title = "Мои заметки"
        toolbarMain.subtitle = "by Rocky"
        toolbarMain.setLogo(R.drawable.ic_note_pad)

        val notePadFragment = NotePadFragment()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerID, notePadFragment)
                .commit()
        }
    }

    override fun onData(data: String?) {
        val bundle = Bundle()
        bundle.putString("text", data)

        val transaction = this.supportFragmentManager.beginTransaction()
        val editNoteFragment = EditNoteFragment()
        editNoteFragment.arguments = bundle

        transaction.replace(R.id.containerID, editNoteFragment)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
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