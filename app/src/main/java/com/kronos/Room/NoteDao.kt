package com.kronos.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kronos.Model.Entity
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: Entity)

    @Update
    suspend fun upDate(note: Entity)

    @Delete
    suspend fun delete(note: Entity)

    @Query("DELETE FROM note_table")
    suspend fun deleteAllNote()

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun getAllNote(): Flow<List<Entity>>
}