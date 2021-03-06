package com.parita.jetpackcomposeapp.itemDecoration

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.parita.jetpackcomposeapp.data.NotesData
import com.parita.jetpackcomposeapp.data.Track
import com.parita.jetpackcomposeapp.ui.theme.*

@Composable
fun NoteItem(
    notesData: NotesData,
    selectedItem: (NotesData) -> Unit,
    itemToSelect: (NotesData) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 2.dp, vertical = 8.dp)
            .width(250.dp)
            .height(150.dp),
        elevation = 2.dp,
        shape = (RoundedCornerShape(corner = CornerSize(20.dp))),
        backgroundColor = BlueViolet1
    ) {
        Row {
            /*Surface(
                modifier = Modifier
                    .size(10.dp)
                    .padding(start = 8.dp, top = 8.dp, end = 0.dp, bottom = 8.dp)
                    .clip(CircleShape)
            ) {
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(ButtonBlue)
                        .padding(10.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_play),
                        contentDescription = "Play",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }*/
            Column(
                modifier = Modifier
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onLongPress = { itemToSelect(notesData) },
                            onTap = { selectedItem(notesData) }
                        )
                    }
                    .fillMaxWidth()
                    .padding(10.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = notesData.noteTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White
                )
                Text(
                    text = notesData.noteDescription,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
                Text(
                    text = "Last modified: " + notesData.noteLastModified,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
            }
        }
    }
}