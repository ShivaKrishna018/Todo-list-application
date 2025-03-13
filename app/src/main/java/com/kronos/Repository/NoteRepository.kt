package com.kronos.Repository

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.annotation.WorkerThread
import com.kronos.Model.Entity
import com.kronos.Room.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepository(val noteDao: NoteDao) {

    val myAllNotes: Flow<List<Entity>> = noteDao.getAllNote()

    @WorkerThread
    suspend fun insert(note: Entity) {
        noteDao.insert(note)
    }

    @WorkerThread
    suspend fun update(note: Entity){
        noteDao.upDate(note)
    }

    @WorkerThread
    suspend fun delete(note: Entity) {
        noteDao.delete(note)
    }
    @WorkerThread
    suspend fun deleteAllNotes() {
        noteDao.deleteAllNote()
    }

}