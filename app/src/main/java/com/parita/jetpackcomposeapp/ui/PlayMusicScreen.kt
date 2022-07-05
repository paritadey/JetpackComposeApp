package com.parita.jetpackcomposeapp.ui

import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.parita.jetpackcomposeapp.R
import com.parita.jetpackcomposeapp.data.TrackDetails
import com.parita.jetpackcomposeapp.itemDecoration.lightColorPath
import com.parita.jetpackcomposeapp.itemDecoration.mediumColorPath
import com.parita.jetpackcomposeapp.ui.theme.*

@Composable
fun PlayMusicScreen(findNavController: NavController, track: TrackDetails) {
    Box(
        modifier = Modifier
            .background(BlueViolet1)
            .fillMaxSize()
    ) {
        Column {
            SetMusicToolbar(findNavController)
            //ShowTrackData(track)
            ShowWebViewContent(track)
        }
    }
}

//music api needs money so not able to load but know how to pass data and open a web-view
@Composable
fun ShowWebViewContent(track: TrackDetails) {
   // val url = track.share.href
    val url = "https://www.geeksforgeeks.org"

    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    }, update = {
        it.loadUrl(url)
    })
}

@Composable
fun ShowTrackData(track: TrackDetails) {
    BoxWithConstraints(
        modifier = Modifier
            .aspectRatio(1f)
            .background(BlueViolet3)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        //Medium color path
        val mediumPath = mediumColorPath(width, height)
        //Light color path
        val lightPath = lightColorPath(width, height, "playMusic")
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(path = mediumPath, color = BlueViolet2)
            drawPath(path = lightPath, color = BlueViolet1)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 20.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(text = track.title, style = MaterialTheme.typography.h2)
                Text(
                    text = track.subtitle,
                    style = MaterialTheme.typography.body1,
                    color = TextWhite
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .padding(horizontal = 5.dp, vertical = 50.dp)
                .fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier
                    .size(250.dp)
                    .clip(CircleShape)
            ) {
                Image(
                    painter = rememberCoilPainter(request = track.images.coverart, fadeIn = true),
                    contentDescription = "Cover art image",
                    modifier = Modifier.clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
fun SetMusicToolbar(findNavController: NavController) {
    Column {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text(text = "SleepOMania", color = Color.White)
            },
            backgroundColor = BlueViolet3,
            navigationIcon = {
                IconButton(onClick = { findNavController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, "Back Button", tint = Color.White)
                }
            })
    }
}
