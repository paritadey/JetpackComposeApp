package com.parita.jetpackcomposeapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.parita.jetpackcomposeapp.ui.theme.DeepBlue
import com.parita.jetpackcomposeapp.R
import com.parita.jetpackcomposeapp.viewmodel.JetpackViewModel


@Composable
fun NoteScreen(findNavController: NavController) {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            SectionNoteGreeting()
        }
    }
}

@Composable
fun SectionNoteGreeting() {
    val viewModel: JetpackViewModel = hiltViewModel()
    var noteQuery = viewModel.noteQuery.value

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 15.dp, 0.dp, 0.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = stringResource(id = R.string.notes_screen),
                style = MaterialTheme.typography.h2
            )
        }
    }
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
                            Icon(Icons.Filled.Search, contentDescription = stringResource(id = R.string.search_image), tint = Color.Black)
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
