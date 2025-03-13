package com.kronos

import android.app.Application
import com.kronos.Repository.NoteRepository
import com.kronos.Room.NoteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NoteApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { NoteDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { NoteRepository(database.getNoteDao()) }

}