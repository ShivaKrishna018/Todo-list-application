package com.kronos.notetakingapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.dialog.MaterialDialogs
import com.kronos.Model.Entity
import com.kronos.NoteAdapter
import com.kronos.NoteApplication
import com.kronos.ViewModel.NoteViewModel
import com.kronos.ViewModel.NoteViewModelFactory
import com.kronos.notetakingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: MaterialToolbar
    lateinit var binding: ActivityMainBinding
    lateinit var noteViewModel: NoteViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var noteAdapter: NoteAdapter
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    lateinit var updateActivityResultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        toolbar = binding.materialToolBar
        setSupportActionBar(toolbar)
        activityResultLauncher()
        recyclerView = binding.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(this)
        noteAdapter = NoteAdapter(this)
        recyclerView.adapter = noteAdapter

        val viewModelFactory = NoteViewModelFactory((application as NoteApplication).repository)
        noteViewModel = ViewModelProvider(this, viewModelFactory).get(NoteViewModel::class.java)
        noteViewModel.myAllNotes.observe(this, Observer { notes ->
            // update ui

            noteAdapter.setNote(notes)

        })

      ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT
              or ItemTouchHelper.RIGHT) {
          override fun onMove(
              recyclerView: RecyclerView,
              viewHolder: RecyclerView.ViewHolder,
              target: RecyclerView.ViewHolder
          ): Boolean {
              TODO("Not yet implemented")
          }

          override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
              noteViewModel.delete(noteAdapter.getNote(viewHolder.adapterPosition))
          }

      }).attachToRecyclerView(recyclerView)
    }


    fun activityResultLauncher()
    {
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ActivityResultCallback {
            result ->
            val resultCode = result.resultCode
            val resultData = result.data

            if(resultCode == RESULT_OK && resultData != null) {
                val insertTitle: String = resultData.getStringExtra("mimeTitle").toString()
                val insertDescription: String = resultData.getStringExtra("mimeDescription").toString()
                val note = Entity(insertTitle, insertDescription)
                noteViewModel.insert(note)
            }
        })

        updateActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback {
                resultUpdate ->
                val updateCode = resultUpdate.resultCode
                val updateData = resultUpdate.data
                if (updateCode== RESULT_OK && updateData != null) {

                    val updateNote: String = updateData.getStringExtra("newNoteIt").toString()
                    val updateDescription: String = updateData.getStringExtra("newDescriptionIt").toString()
                    val updateNoteId = updateData.getIntExtra("noteId", -1)

                    val newNoteMe = Entity(updateNote, updateDescription)
                    newNoteMe.id = updateNoteId
                    noteViewModel.update(newNoteMe)


                }

            })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.new_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.addItem -> {val intent = Intent(this, AddNoteActivity::class.java)
                activityResultLauncher.launch(intent)

                return true
            }

            R.id.deleteItem -> {
                deleteAll()
                return true

            }

            else -> return super.onOptionsItemSelected(item)

        }
    }

    fun deleteAll() {
        val dialog = AlertDialog.Builder(this)

        dialog.setTitle("Delete")
        dialog.setMessage("It will delete all your notes")
        dialog.setNegativeButton("No", DialogInterface.OnClickListener { dialogBox, which ->
            dialogBox.cancel()
        })

        dialog.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogBox, which ->
            noteViewModel.deleteAllNotes()
        })

        dialog.create().show()


    }
}