package com.kronos.notetakingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddNoteActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var editDescription: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        editText = findViewById(R.id.addText)
        editDescription = findViewById(R.id.addDescription)

        val addButton = findViewById<Button>(R.id.add_button)
        val cancel = findViewById<Button>(R.id.cancel_button)

        cancel.setOnClickListener {
            Toast.makeText(this, "nothing saved", Toast.LENGTH_SHORT).show()
            finish()
        }

        addButton.setOnClickListener {
            saveNote()

        }
    }

    fun saveNote() {
        val importTitle = editText.text.toString().trim()
        val importDescription = editDescription.text.toString().trim()

        val intent = Intent()

        intent.putExtra("mimeTitle", importTitle)
        intent.putExtra("mimeDescription", importDescription)
        setResult(RESULT_OK, intent)
        finish()

    }
}