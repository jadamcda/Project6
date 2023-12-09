package com.example.project6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private var firstRun = true
private var noteUpdated = -1
private var noteList: ArrayList<Note> = ArrayList()

class HomeFragment : Fragment() {

    private lateinit var recycler: RecyclerView
    private lateinit var noteAdapter: NoteAdapter

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val noteButton = view.findViewById<Button>(R.id.noteButton)

        recycler = view.findViewById(R.id.recycler)
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(context)

        if(firstRun == false) {
            val title = HomeFragmentArgs.fromBundle(requireArguments()).title
            val description = HomeFragmentArgs.fromBundle(requireArguments()).description
            val note = Note(title, description)

            if(noteUpdated >= 0){
                noteList.set(noteUpdated, note)
                noteAdapter = NoteAdapter(noteList)
                recycler.adapter = noteAdapter
            }
            else{
                noteList.add(note)
                noteAdapter = NoteAdapter(noteList)
                recycler.adapter = noteAdapter
            }

            noteAdapter.onNoteClick = {
                noteUpdated = noteList.indexOf(it)

                val action = HomeFragmentDirections.actionHomeFragmentToNoteFragment(it.title, it.description)
                view.findNavController().navigate(action)

            }

            noteAdapter.onButtonClick = {
                noteList.remove(it)
                noteAdapter.thisNoteList = noteList
                recycler.adapter = noteAdapter
            }
        }

        noteButton.setOnClickListener {
            firstRun = false
            noteUpdated = -1

            val action = HomeFragmentDirections.actionHomeFragmentToNoteFragment("", "")
            view.findNavController().navigate(action)
        }


        return view
    }
}