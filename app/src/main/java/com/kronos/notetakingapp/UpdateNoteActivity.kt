package com.kronos.notetakingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UpdateNoteActivity : AppCompatActivity() {
    lateinit var editTextUpdate: EditText
    lateinit var editDescriptionUpdate: EditText
    lateinit var cancelUpdate: Button
    lateinit var addUpdate: Button
    var currentId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_note)

        editTextUpdate = findViewById(R.id.addTextUpdate)
        editDescriptionUpdate = findViewById(R.id.addDescriptionUpdate)
        cancelUpdate = findViewById(R.id.cancel_buttonUpdate)
        addUpdate  = findViewById(R.id.add_buttonUpdate)
        getAndSetNote()

        cancelUpdate.setOnClickListener {
            finish()
        }
        addUpdate.setOnClickListener {

            UpdateNote()
        }

    }

    fun UpdateNote() {

        val newNote = editTextUpdate.text.toString().trim()
        val newDescription = editDescriptionUpdate.text.toString().trim()

        val intent = Intent()
        intent.putExtra("newNoteIt", newNote)
        intent.putExtra("newDescriptionIt", newDescription)
        if (currentId != -1 ) {
            intent.putExtra("noteId", currentId)
            setResult(RESULT_OK, intent)
            finish()
        }

    }

    fun getAndSetNote() {
        val currentTitle = intent.getStringExtra("updateTitle")
        val currentDescription = intent.getStringExtra("updateDescription")
        currentId = intent.getIntExtra("updateId", -1)

        editTextUpdate.setText(currentTitle)
        editDescriptionUpdate.setText(currentDescription)


    }
}