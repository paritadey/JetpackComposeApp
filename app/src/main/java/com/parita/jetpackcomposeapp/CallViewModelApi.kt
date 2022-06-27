package com.parita.jetpackcomposeapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.parita.jetpackcomposeapp.data.Track
import com.parita.jetpackcomposeapp.util.Resource
import com.parita.jetpackcomposeapp.viewmodel.JetpackViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalMaterialApi
@Composable
fun CallViewModelApi(viewModel: JetpackViewModel = hiltViewModel()):ArrayList<Track>?{
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val getAllUserData = viewModel.hitList.observeAsState()
    scope.launch {
        val result = viewModel.getMusicData()
        if (result is Resource.Success) {
            Log.d("TAG", "Result : ${getAllUserData.value}")
        } else if (result is Resource.Error) {
            Log.d("TAG", "Error: ${result.message}")
        }
    }
    return getAllUserData.value
}