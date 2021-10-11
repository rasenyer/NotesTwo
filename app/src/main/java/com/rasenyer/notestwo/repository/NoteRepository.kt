package com.rasenyer.notestwo.repository

//--------------------------------------------------------------------------------------------------

import androidx.lifecycle.LiveData
import com.rasenyer.notestwo.db.NoteDatabase
import com.rasenyer.notestwo.model.Note

//--------------------------------------------------------------------------------------------------

class NoteRepository(noteDatabase: NoteDatabase) {

//--------------------------------------------------------------------------------------------------
    
    private val noteDao = noteDatabase.getNoteDao()

//--------------------------------------------------------------------------------------------------

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

//--------------------------------------------------------------------------------------------------

    suspend fun update(note: Note) {
        noteDao.update(note)
    }

//--------------------------------------------------------------------------------------------------

    suspend fun delete(note:Note) {
        noteDao.delete(note)
    }

//--------------------------------------------------------------------------------------------------

    fun searchNote(query: String?): LiveData<List<Note>> {
        return noteDao.searchNote(query)
    }

//--------------------------------------------------------------------------------------------------

    val getNotes: LiveData<List<Note>> = noteDao.getNotes()

//--------------------------------------------------------------------------------------------------

}