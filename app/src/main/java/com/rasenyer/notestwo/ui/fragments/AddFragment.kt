package com.rasenyer.notestwo.ui.fragments

//--------------------------------------------------------------------------------------------------

import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add.*
import android.os.Bundle
import android.widget.Toast
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.rasenyer.notestwo.R
import com.rasenyer.notestwo.model.Note
import com.rasenyer.notestwo.ui.activity.MainActivity
import com.rasenyer.notestwo.viewmodel.NoteViewModel

//--------------------------------------------------------------------------------------------------

class AddFragment : Fragment() {

//--------------------------------------------------------------------------------------------------

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var mView: View

//--------------------------------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

//--------------------------------------------------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

//--------------------------------------------------------------------------------------------------

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mView = view
        noteViewModel = (activity as MainActivity).noteViewModel
    }

//--------------------------------------------------------------------------------------------------

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_add, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

//--------------------------------------------------------------------------------------------------

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.mItemSave -> { insert(mView) }
        }
        return super.onOptionsItemSelected(item)
    }

//--------------------------------------------------------------------------------------------------

    private fun insert(view: View) {

        val title = mEditTextTitle.text.toString().trim()
        val description = mEditTextDescription.text.toString().trim()

        if(title.isNotEmpty()) {

            val note = Note(0, title, description)
            noteViewModel.insert(note)

            Snackbar.make(view, R.string.note_saved_successfully, Snackbar.LENGTH_LONG).show()
            view.findNavController().navigate(R.id.action_addFragment_to_homeFragment)

        } else {
            Toast.makeText(activity, R.string.please_enter_title, Toast.LENGTH_LONG).show()
        }

    }

//--------------------------------------------------------------------------------------------------

}