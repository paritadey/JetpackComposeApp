package com.parita.jetpackcomposeapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.findNavController
import com.parita.jetpackcomposeapp.data.NotesData
import com.parita.jetpackcomposeapp.ui.AddNoteScreen
import com.parita.jetpackcomposeapp.ui.theme.MeditationUITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : Fragment() {
    var noteArrayList = arrayListOf<NotesData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments!=null){
            noteArrayList = arguments!!.getParcelableArrayList("noteList")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MeditationUITheme {
                    AddNoteScreen(findNavController(), noteArrayList)
                }
            }
        }
    }
}