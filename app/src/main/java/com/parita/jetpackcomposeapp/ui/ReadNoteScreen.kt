package com.parita.jetpackcomposeapp.ui

import android.util.Log
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.parita.jetpackcomposeapp.R
import com.parita.jetpackcomposeapp.data.NotesData
import com.parita.jetpackcomposeapp.ui.theme.DeepBlue
import com.parita.jetpackcomposeapp.ui.theme.TextWhite
import com.parita.jetpackcomposeapp.viewmodel.JetpackViewModel

@Composable
fun ReadNoteScreen(findNavController: NavController, notesData: NotesData) {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            //SectionNoteGreeting(JetpackConstant.RNS)
            ToolBar(findNavController)
            NoteDetails(notesData)
            ExpandedFloatingButton(findNavController, notesData)
        }
    }
}

@Composable
fun ExpandedFloatingButton(findNavController: NavController, notesData: NotesData) {
    var multiFloatingState by remember {
        mutableStateOf(MultiFloatingState.Collapsed)
    }
    val items = listOf(
        MinFabItem(
            icon = ImageBitmap.imageResource(id = R.drawable.edit),
            label = "Edit Note",
            identifier = "Edit Note"
        ),
        MinFabItem(
            icon = ImageBitmap.imageResource(id = R.drawable.delete),
            label = "Delete Note",
            identifier = "Delete Note"
        )
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.End
    ) {
        MultiFloatingButton(
            multiFloatingState = multiFloatingState,
            onMultiFabStateChange = { multiFloatingState = it },
            items = items, notesData, findNavController
        )
    }
}

@Composable
fun MultiFloatingButton(
    multiFloatingState: MultiFloatingState,
    onMultiFabStateChange: (MultiFloatingState) -> Unit,
    items: List<MinFabItem>,
    notesData: NotesData,
    findNavController: NavController
) {
    val viewModel: JetpackViewModel = hiltViewModel()
    val transition = updateTransition(targetState = multiFloatingState, label = "transition")
    val rotate by transition.animateFloat(label = "rotate") {
        if (it == MultiFloatingState.Expanded) 315f else 0f
    }
    val fabScale by transition.animateFloat(label = "FabScale") {
        if (it == MultiFloatingState.Expanded) 70f else 0f
    }
    val alpha by transition.animateFloat(
        label = "alpha",
        transitionSpec = { tween(durationMillis = 50) }) {
        if (it == MultiFloatingState.Expanded) 1f else 0f
    }
    val showDeleteDialogState: Boolean by viewModel.showDeleteDialog.collectAsState()

    if (showDeleteDialogState) {
        showDeleteAlert(
            msg = stringResource(id = R.string.delete_note_alert),
            showDialog = showDeleteDialogState,
            onDismiss = viewModel::onDeleteDialogDismiss,
            onConfirm = { viewModel.onDeleteDialogConfirm(findNavController, "ReadNoteScreen") },
            viewModel
        )
    }
    Column(horizontalAlignment = Alignment.End) {
        if (transition.currentState == MultiFloatingState.Expanded) {
            items.forEach { it ->
                MinFab(item = it, alpha, fabScale, onMinFabItemClick = {
                    if (it.label.equals("Delete Note")) {
                        Log.d("TAG", "Delete button pressed")
                        viewModel.onOpenDeleteDialogClicked(notesData.noteId)
                    } else if (it.label.equals("Edit Note")) {
                        Log.d("TAG", "Edit button pressed")
                    }
                })
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
        FloatingActionButton(
            onClick = {
                onMultiFabStateChange(
                    if (transition.currentState == MultiFloatingState.Expanded) {
                        MultiFloatingState.Collapsed
                    } else {
                        MultiFloatingState.Expanded
                    }
                )
            },
            modifier = Modifier.rotate(rotate)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Click to operate",
                modifier = Modifier.rotate(rotate)
            )
        }
    }
}

@Composable
fun MinFab(
    item: MinFabItem,
    alpha: Float,
    fabScale: Float,
    onMinFabItemClick: (MinFabItem) -> Unit
) {
    val buttonColors = MaterialTheme.colors.secondary
    val shadow = Color.Black.copy(1f)

    Canvas(
        modifier = Modifier
            .size(50.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                onClick = {
                    onMinFabItemClick.invoke(item)
                },
                indication = rememberRipple(
                    bounded = false,
                    radius = 25.dp, color = MaterialTheme.colors.onSurface
                )
            )
    ) {
        drawCircle(
            color = shadow,
            radius = fabScale,
            center = Offset(center.x + 2f, center.y + 2f)
        )
        drawCircle(color = buttonColors, radius = fabScale)
        drawImage(
            image = item.icon,
            topLeft = Offset(center.x - (item.icon.width / 2), center.y - (item.icon.width / 2)),
            alpha = alpha
        )
    }
}

@Composable
fun NoteDetails(notesData: NotesData) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 15.dp, 0.dp, 0.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = notesData.noteTitle,
                color = TextWhite,
                style = MaterialTheme.typography.h2
            )
            Text(
                text = "Last modified: " + notesData.noteLastModified + "  | " + notesData.noteDescription.length + " Characters",
                color = TextWhite,
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = notesData.noteDescription,
                color = TextWhite,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
fun ToolBar(findNavController: NavController) {
    Column {
        TopAppBar(
            title = {
                Text("")
            },
            backgroundColor = DeepBlue,
            navigationIcon = {
                IconButton(onClick = { findNavController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, "Back Arrow", tint = Color.White)
                }
            }, actions = {
                IconButton(onClick = {/* Do Something*/ }) {
                    Icon(Icons.Filled.Share, "Share", tint = Color.White)
                }
            })
    }
}

enum class MultiFloatingState {
    Expanded,
    Collapsed
}

class MinFabItem(val icon: ImageBitmap, val label: String, val identifier: String)