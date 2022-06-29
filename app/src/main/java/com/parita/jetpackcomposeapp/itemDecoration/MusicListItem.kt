package com.parita.jetpackcomposeapp.itemDecoration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import com.parita.jetpackcomposeapp.data.Track

@Composable
fun MusicListItem(track: Track) {
    Card(
        modifier = Modifier
            .padding(horizontal = 2.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 0.dp,
        //  shape = (RoundedCornerShape(corner = CornerSize(10.dp))),
        backgroundColor = Color.Transparent
    ) {
        Row {
            Surface(
                modifier = Modifier
                    .size(90.dp)
                    .padding(start = 8.dp, top = 8.dp, end = 0.dp, bottom = 8.dp)
                    .clip(CircleShape)
            ) {
                //Coil image
                val image = rememberCoilPainter(request = track.images.coverart, fadeIn = true)
                Image(
                    painter = image,
                    contentDescription = "Track Image",
                    modifier = Modifier.clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = track.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White
                )
                Text(
                    text = track.subtitle,
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}
