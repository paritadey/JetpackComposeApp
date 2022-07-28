package com.parita.jetpackcomposeapp.ui

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.parita.jetpackcomposeapp.R
import com.parita.jetpackcomposeapp.data.NotesData
import com.parita.jetpackcomposeapp.itemDecoration.NoteItem
import com.parita.jetpackcomposeapp.ui.theme.*
import com.parita.jetpackcomposeapp.util.JetpackConstant.ANS1
import com.parita.jetpackcomposeapp.util.JetpackConstant.ANS2
import com.parita.jetpackcomposeapp.util.JetpackConstant.NLS
import com.parita.jetpackcomposeapp.viewmodel.JetpackViewModel

@Composable
fun NoteListScreen(findNavController: NavController) {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            SectionNoteGreeting(NLS)
            SectionSearch()
            ShowRecyclerView()
            ShowFloatingButton(findNavController)
        }
    }
}

@Composable
fun SectionNoteGreeting(screenName: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 15.dp, 0.dp, 0.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = if (screenName.equals(NLS) || screenName.equals(ANS1)) {
                    stringResource(id = R.string.notes_screen)
                } else if (screenName.equals(ANS2)) {
                    stringResource(id = R.string.add_note_screen_2)
                } else {
                    stringResource(id = R.string.app_name)
                },
                style = MaterialTheme.typography.h2
            )
        }
    }
}

@Composable
fun SectionSearch() {
    val viewModel: JetpackViewModel = hiltViewModel()
    var noteQuery = viewModel.noteQuery.value
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = DeepBlue,
                elevation = 8.dp
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(top = 2.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
                        value = noteQuery,
                        onValueChange = {
                            // viewModel.onQueryChanged(it)
                        },
                        label = { Text(text = stringResource(id = R.string.search_image)) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text, imeAction = ImeAction.Search
                        ),
                        leadingIcon = {
                            Icon(
                                Icons.Filled.Search,
                                contentDescription = stringResource(id = R.string.search_image),
                                tint = Color.Black
                            )
                        },
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                //  viewModel.newSearch(query)
                            }
                        ),
                        textStyle = TextStyle(color = Color.Black),
                        maxLines = 1,
                        singleLine = true,
                        shape = CircleShape,
                        colors = TextFieldDefaults.textFieldColors( // remove the bottom border of textfield
                            textColor = Color.Black,
                            disabledTextColor = Color.Transparent,
                            backgroundColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun ShowRecyclerView() {
    val viewModel: JetpackViewModel = hiltViewModel()
    var list = viewModel.noteList.collectAsState(initial = listOf())
    val noteArrayList = arrayListOf<NotesData>()
    list.value.forEach {
        noteArrayList.add(it)
    }
    if (noteArrayList.isEmpty()) {
        Column {
            Text(
                text = stringResource(id = R.string.no_data_found),
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(15.dp)
            )
        }
        Column {
            Text(
                text = stringResource(id = R.string.advice),
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(15.dp)
            )
        }
    } else {
        Column {
            Text(
                text = stringResource(id = R.string.check_out_notes),
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(15.dp)
            )
        }
        var featureNotes = noteArrayList
        LazyColumn(contentPadding = PaddingValues(10.dp)) {
            items(featureNotes.size) {
                NoteItem(featureNotes[it])
            }
        }
    }
}

@Composable
fun ShowFloatingButton(findNavController: NavController) {
    val viewModel: JetpackViewModel = hiltViewModel()
    var list = viewModel.noteList.collectAsState(initial = listOf())
    val noteArrayList = arrayListOf<NotesData>()
    list.value.forEach {
        noteArrayList.add(it)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.End
    ) {
        FloatingActionButton(
            onClick = {
                findNavController.navigate(R.id.notesListToAddNote, Bundle().apply {
                    putSerializable("noteList", noteArrayList)
                })
            },
            backgroundColor = BlueViolet1, contentColor = Color.White
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add New Note button")
        }
    }
}