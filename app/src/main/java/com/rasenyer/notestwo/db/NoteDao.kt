package com.rasenyer.notestwo.db

//--------------------------------------------------------------------------------------------------

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rasenyer.notestwo.model.Note

//--------------------------------------------------------------------------------------------------

@Dao
interface NoteDao {

//--------------------------------------------------------------------------------------------------

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

//--------------------------------------------------------------------------------------------------

    @Update
    suspend fun update(note: Note)

//--------------------------------------------------------------------------------------------------

    @Delete
    suspend fun delete(note:Note)

//--------------------------------------------------------------------------------------------------

    @Query("SELECT * FROM note_table WHERE title LIKE :query OR description LIKE :query")
    fun searchNote(query: String?): LiveData<List<Note>>

//--------------------------------------------------------------------------------------------------

    @Query("SELECT * FROM note_table ORDER BY id DESC")
    fun getNotes(): LiveData<List<Note>>

//--------------------------------------------------------------------------------------------------

}