package com.parita.jetpackcomposeapp.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.parita.jetpackcomposeapp.R
import com.parita.jetpackcomposeapp.data.TrackDetails
import com.parita.jetpackcomposeapp.ui.theme.DeepBlue

@Composable
fun PlayMusicScreen(findNavController:NavController, track: TrackDetails) {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            SetMusicToolbar(findNavController)
            ShowTrackData(track)
        }
    }
}

@Composable
fun ShowTrackData(track: TrackDetails) {
    Log.d("TAG", "Fetched track details:${track}")
}

@Composable
fun SetMusicToolbar(findNavController: NavController) {
    Column {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text(text = "SleepOMania", color = Color.White)
            },
            backgroundColor = DeepBlue,
            navigationIcon = {
                IconButton(onClick = { findNavController.navigate(R.id.playBackToSearch) }) {
                    Icon(Icons.Filled.ArrowBack, "Back Button")
                }
            })
    }
}
