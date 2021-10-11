package com.rasenyer.notestwo.viewmodel

//--------------------------------------------------------------------------------------------------

import android.app.Application
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.rasenyer.notestwo.db.NoteDatabase
import com.rasenyer.notestwo.model.Note
import com.rasenyer.notestwo.repository.NoteRepository

//--------------------------------------------------------------------------------------------------

class NoteViewModel(application: Application): AndroidViewModel(application) {

//--------------------------------------------------------------------------------------------------

    private val noteRepository: NoteRepository

//--------------------------------------------------------------------------------------------------

    fun insert(note: Note) {
        viewModelScope.launch(Dispatchers.IO) { noteRepository.insert(note) }
    }

//--------------------------------------------------------------------------------------------------

    fun update(note: Note) {
        viewModelScope.launch(Dispatchers.IO) { noteRepository.update(note) }
    }

//--------------------------------------------------------------------------------------------------

    fun delete(note: Note) {
        viewModelScope.launch(Dispatchers.IO) { noteRepository.delete(note) }
    }

//--------------------------------------------------------------------------------------------------

    fun searchNote(query: String?): LiveData<List<Note>> { return noteRepository.searchNote(query) }

//--------------------------------------------------------------------------------------------------

    val getNotes: LiveData<List<Note>>

//--------------------------------------------------------------------------------------------------

    init {
        val noteDatabase = NoteDatabase.getDatabase(application)
        noteRepository = NoteRepository(noteDatabase)
        getNotes = noteRepository.getNotes
    }

//--------------------------------------------------------------------------------------------------

}