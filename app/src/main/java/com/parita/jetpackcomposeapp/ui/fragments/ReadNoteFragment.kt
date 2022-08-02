package com.parita.jetpackcomposeapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.findNavController
import com.parita.jetpackcomposeapp.data.NotesData
import com.parita.jetpackcomposeapp.ui.ReadNoteScreen
import com.parita.jetpackcomposeapp.ui.theme.MeditationUITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReadNoteFragment : Fragment() {

    lateinit var notesData: NotesData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            notesData = arguments!!.getParcelable("note_data")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MeditationUITheme {
                    ReadNoteScreen(findNavController(), notesData)
                }
            }
        }
    }
}