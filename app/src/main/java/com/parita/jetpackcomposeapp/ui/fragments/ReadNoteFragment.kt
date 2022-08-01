package com.parita.jetpackcomposeapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.findNavController
import com.parita.jetpackcomposeapp.R
import com.parita.jetpackcomposeapp.data.NotesData
import com.parita.jetpackcomposeapp.ui.AddNoteScreen
import com.parita.jetpackcomposeapp.ui.ReadNoteScreen
import com.parita.jetpackcomposeapp.ui.theme.MeditationUITheme

class ReadNoteFragment : Fragment() {

    lateinit var notesData: NotesData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            notesData = arguments!!.getParcelable("note_data")!!
        }
        Log.d("TAG", "Note details: ${notesData}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MeditationUITheme {
                    ReadNoteScreen(findNavController())
                }
            }
        }
    }
}