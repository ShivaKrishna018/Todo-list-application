package com.kronos.Room

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kronos.Model.Entity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [Entity::class], version = 1)
abstract class NoteDatabase() : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object {
        @Volatile
        var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NoteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"

                )
                    .addCallback(NoteDatabaseCallBack(scope))
                    .build()

                INSTANCE = instance

                instance


            }

        }

    }
    private class NoteDatabaseCallBack(private var scope: CoroutineScope): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
               // database.getNoteDao().insert(Note("t", "d"))

                scope.launch {
                    val noteInfo = database.getNoteDao()
                    noteInfo.insert(Entity("title", "description"))
                    noteInfo.insert(Entity("title 2", "description 2"))
                }
            }
        }
    }

}