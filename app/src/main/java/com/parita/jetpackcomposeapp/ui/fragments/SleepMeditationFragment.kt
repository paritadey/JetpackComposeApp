package com.parita.jetpackcomposeapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.findNavController
import com.parita.jetpackcomposeapp.data.Track
import com.parita.jetpackcomposeapp.ui.CustomCircularProgress
import com.parita.jetpackcomposeapp.ui.ShowMessage
import com.parita.jetpackcomposeapp.ui.ShowMusicData
import com.parita.jetpackcomposeapp.ui.SleepMeditationScreen
import com.parita.jetpackcomposeapp.ui.theme.MeditationUITheme
import com.parita.jetpackcomposeapp.util.Resource
import com.parita.jetpackcomposeapp.viewmodel.JetpackViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SleepMeditationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MeditationUITheme {
                    SleepMeditationScreen(findNavController())
                }
            }
        }
    }
}