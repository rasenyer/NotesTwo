package com.rasenyer.notestwo.ui.fragments

//--------------------------------------------------------------------------------------------------

import android.view.*
import android.os.Bundle
import android.widget.Toast
import android.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_edit.*
import com.rasenyer.notestwo.R
import com.rasenyer.notestwo.model.Note
import com.rasenyer.notestwo.ui.activity.MainActivity
import com.rasenyer.notestwo.viewmodel.NoteViewModel

//--------------------------------------------------------------------------------------------------

class EditFragment : Fragment() {

//--------------------------------------------------------------------------------------------------

    private val editFragmentArgs: EditFragmentArgs by navArgs()
    private lateinit var currentNote: Note
    private lateinit var noteViewModel: NoteViewModel

//--------------------------------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

//--------------------------------------------------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

//--------------------------------------------------------------------------------------------------

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//--------------------------------------------------------------------------------------------------

        noteViewModel = (activity as MainActivity).noteViewModel
        currentNote = editFragmentArgs.note!!

//--------------------------------------------------------------------------------------------------

        mEditTextTitle.setText(currentNote.title)
        mEditTextDescription.setText(currentNote.description)

//--------------------------------------------------------------------------------------------------

        mFloatingActionButton.setOnClickListener {

            val title = mEditTextTitle.text.toString().trim()
            val description = mEditTextDescription.text.toString().trim()

            if(title.isNotEmpty()){

                val note = Note(currentNote.id, title, description)
                noteViewModel.update(note)

                Snackbar.make(view, R.string.note_updated_successfully, Snackbar.LENGTH_LONG).show()
                view.findNavController().navigate(R.id.action_editFragment_to_homeFragment)

            } else {
                Toast.makeText(activity, R.string.please_enter_title, Toast.LENGTH_LONG).show()
            }

        }

//--------------------------------------------------------------------------------------------------

    }

//--------------------------------------------------------------------------------------------------

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_edit, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

//--------------------------------------------------------------------------------------------------

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mItemDelete -> { delete() }
        }
        return super.onOptionsItemSelected(item)
    }

//--------------------------------------------------------------------------------------------------

    private fun delete() {

        AlertDialog.Builder(activity).apply {
            setTitle(R.string.delete_note)
            setMessage(R.string.are_you_sure)
            setPositiveButton(R.string.delete) { _, _ ->
                noteViewModel.delete(currentNote)
                view?.findNavController()?.navigate(R.id.action_editFragment_to_homeFragment)
            }
            setNegativeButton(R.string.cancel, null)
        }.create().show()

    }

//--------------------------------------------------------------------------------------------------

}