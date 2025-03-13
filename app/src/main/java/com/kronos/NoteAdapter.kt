package com.kronos

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.kronos.Model.Entity
import com.kronos.notetakingapp.MainActivity
import com.kronos.notetakingapp.R
import com.kronos.notetakingapp.UpdateNoteActivity

class NoteAdapter(private var activity: MainActivity) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    var notes: List<Entity> = ArrayList()
    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
         val noteText = itemView.findViewById<TextView>(R.id.NoteTxt)
        val noteDescriptor = itemView.findViewById<TextView>(R.id.DescriptionTxt)
        val cardView = itemView.findViewById<CardView>(R.id.cardView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote: Entity = notes[position]
        holder.noteText.text = currentNote.title
        holder.noteDescriptor.text = currentNote.description
        holder.cardView.setOnClickListener {
            val intent = Intent(activity, UpdateNoteActivity::class.java)
            intent.putExtra("updateTitle", currentNote.title)
            intent.putExtra("updateDescription", currentNote.description)
            intent.putExtra("updateId", currentNote.id)

            activity.updateActivityResultLauncher.launch(intent)

        }
    }

    override fun getItemCount(): Int {
       return notes.size
    }

    fun setNote(newNote: List<Entity>) {
        this.notes = newNote
        notifyDataSetChanged()
    }

    fun getNote(position: Int): Entity {
        return notes[position]
    }
}