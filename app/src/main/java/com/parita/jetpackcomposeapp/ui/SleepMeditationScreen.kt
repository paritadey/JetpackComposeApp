package com.parita.jetpackcomposeapp.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.parita.jetpackcomposeapp.R
import com.parita.jetpackcomposeapp.data.Track
import com.parita.jetpackcomposeapp.itemDecoration.MusicListItem
import com.parita.jetpackcomposeapp.ui.theme.DeepBlue
import com.parita.jetpackcomposeapp.util.Resource
import com.parita.jetpackcomposeapp.viewmodel.JetpackViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@Composable
fun SleepMeditationScreen(findNavController: NavController) {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            SetToolbar(findNavController)
            CallApi()
        }
    }
}

@Composable
fun CallApi(viewModel: JetpackViewModel = hiltViewModel()) {
    val scope = rememberCoroutineScope()
    val getAllMusicData = viewModel.hitList.observeAsState()

    scope.launch {
        var result = viewModel.getMusicData("Kiss the rain")
        if (result is Resource.Success) {
            scope.cancel()
        } else if (result is Resource.Error) {
            Log.d("TAG", "Error: ${result.message}")
        }
    }
    if (!viewModel.isLoading.value) {
        if (viewModel.hitList.value!!.isNotEmpty()) {
            Log.d("Tag", "Result data: ${getAllMusicData.value}")
            ShowMessage()
            getAllMusicData.value?.let { ShowMusicData(it) }
        }
    } else if (viewModel.isLoading.value) {
        CustomCircularProgress(isDisplay = true)
    }
}

@Composable
fun SetToolbar(findNavController: NavController) {
    Column {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text(text = stringResource(id = R.string.app_name), color = Color.White)
            },
            backgroundColor = MaterialTheme.colors.primary,
            navigationIcon = {
                IconButton(onClick = { findNavController.navigate(R.id.sleepMeditationBackHome) }) {
                    Icon(Icons.Filled.ArrowBack, stringResource(id = R.string.back_button))
                }
            })
    }
}

@Composable
fun CustomCircularProgress(isDisplay: Boolean) {
    if (isDisplay) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                color = Color.White
            )
        }
    }
}

@Composable
fun ShowMessage() {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = stringResource(id = R.string.soothing_music),
                color = Color.White,
                style = MaterialTheme.typography.h2
            )
        }
    }
}

@Composable
fun ShowMusicData(result: ArrayList<Track>) {
    LazyColumn(contentPadding = PaddingValues(12.dp)) {
        items(items = result) {
            MusicListItem(it)
        }
    }
}
