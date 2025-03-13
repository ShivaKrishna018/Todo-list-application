package com.kronos.Model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName ="note_table")
data class Entity (var title: String, var description: String){

    @PrimaryKey(autoGenerate = true)
    var id = 0
}