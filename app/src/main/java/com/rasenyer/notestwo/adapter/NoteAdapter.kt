package com.rasenyer.notestwo.adapter

//--------------------------------------------------------------------------------------------------

import kotlinx.android.synthetic.main.item_note.view.*
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rasenyer.notestwo.R
import com.rasenyer.notestwo.model.Note
import com.rasenyer.notestwo.ui.fragments.HomeFragmentDirections
import com.rasenyer.notestwo.utils.ColorPicker

//--------------------------------------------------------------------------------------------------

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NotesViewHolder>() {

//--------------------------------------------------------------------------------------------------

    inner class NotesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

//--------------------------------------------------------------------------------------------------

    private val differCallback = object : DiffUtil.ItemCallback<Note>() {

        override fun areItemsTheSame(oldNote: Note, newNote: Note): Boolean {
             return oldNote.id == newNote.id
        }

        override fun areContentsTheSame(oldNote: Note, newNote: Note): Boolean {
          return oldNote == newNote
        }

    }

//--------------------------------------------------------------------------------------------------

    val listDiffer = AsyncListDiffer(this, differCallback)

//--------------------------------------------------------------------------------------------------

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent,false)
        return NotesViewHolder(view)
    }

//--------------------------------------------------------------------------------------------------

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        val note = listDiffer.currentList[position]

        holder.itemView.apply {

            mTextViewTitle.text = note.title
            mTextViewDescription.text = note.description

            val color = Color.parseColor(ColorPicker.getColor())
            mViewColor.setBackgroundColor(color)

        }.setOnClickListener{ view ->

           val direction = HomeFragmentDirections.actionHomeFragmentToEditFragment(note)
            view.findNavController().navigate(direction)

        }

    }

//--------------------------------------------------------------------------------------------------

    override fun getItemCount(): Int { return listDiffer.currentList.size }

//--------------------------------------------------------------------------------------------------

}