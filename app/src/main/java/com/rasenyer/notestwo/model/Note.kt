package com.rasenyer.notestwo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "note_table")
data class Note(

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    var title: String,

    var description: String

    ): Serializable
