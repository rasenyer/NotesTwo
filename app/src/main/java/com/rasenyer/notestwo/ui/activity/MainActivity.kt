package com.rasenyer.notestwo.ui.activity

//--------------------------------------------------------------------------------------------------

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rasenyer.notestwo.R
import com.rasenyer.notestwo.viewmodel.NoteViewModel
import com.rasenyer.notestwo.viewmodel.NoteViewModelFactory

//--------------------------------------------------------------------------------------------------

class MainActivity : AppCompatActivity(){

//--------------------------------------------------------------------------------------------------

    lateinit var noteViewModel: NoteViewModel

//--------------------------------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(mToolbar)

//--------------------------------------------------------------------------------------------------

        val noteViewModelFactory = NoteViewModelFactory(application)
        noteViewModel = ViewModelProvider(this,noteViewModelFactory)[NoteViewModel::class.java]

//--------------------------------------------------------------------------------------------------

    }

//--------------------------------------------------------------------------------------------------

}