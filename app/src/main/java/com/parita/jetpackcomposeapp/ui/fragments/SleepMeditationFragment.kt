package com.parita.jetpackcomposeapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import com.parita.jetpackcomposeapp.ui.theme.MeditationUITheme
import com.parita.jetpackcomposeapp.util.Resource
import com.parita.jetpackcomposeapp.viewmodel.JetpackViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@AndroidEntryPoint
class SleepMeditationFragment : Fragment() {

   // val viewModel: JetpackViewModel by viewModels()

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
                    Text(text = "Sleep meditation fragment", color = Color.White)
                    CallApi()
                }
            }
        }
    }
}

@Composable
fun CallApi(viewModel: JetpackViewModel = hiltViewModel()){
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val getAllUserData = viewModel.hitList.observeAsState()

    scope.launch {
        var result = viewModel.getMusicData()
        if (result is Resource.Success) {
            Toast.makeText(context, "Fetching data success!", Toast.LENGTH_SHORT).show()
            scope.cancel()
        } else if (result is Resource.Error) {
            Toast.makeText(context, "Error: ${result.message}", Toast.LENGTH_SHORT)
                .show()
        }
    }
    if(viewModel.isLoading.value){
        if(viewModel.hitList.value!!.isNotEmpty()){
            Log.d("Tag", "Result data: ${getAllUserData.value}")
        }
    }
}