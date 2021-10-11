package com.rasenyer.notestwo.ui.fragments

//--------------------------------------------------------------------------------------------------

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import com.rasenyer.notestwo.R
import com.rasenyer.notestwo.adapter.NoteAdapter
import com.rasenyer.notestwo.model.Note
import com.rasenyer.notestwo.ui.activity.MainActivity
import com.rasenyer.notestwo.viewmodel.NoteViewModel

//--------------------------------------------------------------------------------------------------

class HomeFragment : Fragment(), SearchView.OnQueryTextListener {

//--------------------------------------------------------------------------------------------------

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter

//--------------------------------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

//--------------------------------------------------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

//--------------------------------------------------------------------------------------------------

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = (activity as MainActivity).noteViewModel
        setUpRecyclerView()

        mFloatingActionButton.setOnClickListener { it.findNavController().navigate(R.id.action_homeFragment_to_addFragment) }

    }

//--------------------------------------------------------------------------------------------------

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_home, menu)

        val mMenuSearch = menu.findItem(R.id.mItemSearch).actionView as SearchView
        mMenuSearch.isSubmitButtonEnabled = true
        mMenuSearch.setOnQueryTextListener(this)

    }

//--------------------------------------------------------------------------------------------------

    override fun onQueryTextSubmit(query: String?): Boolean {

        if (query != null){ searchNote(query) }
        return true

    }

//--------------------------------------------------------------------------------------------------

    override fun onQueryTextChange(newText: String?): Boolean {

        if (newText != null){ searchNote(newText) }
        return true

    }

//--------------------------------------------------------------------------------------------------

    private fun setUpRecyclerView() {

        noteAdapter = NoteAdapter()

        mRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = noteAdapter
        }

        activity?.let {
            noteViewModel.getNotes.observe(viewLifecycleOwner, { noteList->
                noteAdapter.listDiffer.submitList(noteList)
                updateUI(noteList)
            })
        }

    }

//--------------------------------------------------------------------------------------------------

    private fun updateUI(noteList: List<Note>){

        if(noteList.isNotEmpty()) {
            mCardView.visibility = View.GONE
            mRecyclerView.visibility = View.VISIBLE
        } else {
            mCardView.visibility = View.VISIBLE
            mRecyclerView.visibility = View.GONE
        }

    }

//--------------------------------------------------------------------------------------------------

    private fun searchNote(query: String?){

        val searchQuery = "%$query%"
        noteViewModel.searchNote(searchQuery).observe(this, {list -> noteAdapter.listDiffer.submitList(list) })

    }

//--------------------------------------------------------------------------------------------------

}