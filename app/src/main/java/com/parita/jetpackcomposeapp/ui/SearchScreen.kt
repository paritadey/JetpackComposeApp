package com.parita.jetpackcomposeapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.parita.jetpackcomposeapp.ui.theme.DeepBlue
import com.parita.jetpackcomposeapp.viewmodel.JetpackViewModel

@Composable
fun SearchScreen() {
    Scaffold(backgroundColor = DeepBlue) {
        SearchView()
    }
}

@Composable
fun SearchView(viewModel: JetpackViewModel = hiltViewModel()) {
    val musicList = viewModel.hitList.observeAsState()
    var query = viewModel.query.value
    Column {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = DeepBlue,
            elevation = 8.dp
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(top = 30.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
                    value = query,
                    onValueChange = {
                        viewModel.onQueryChanged(it)
                    },
                    label = { Text(text = "Search") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Search
                    ),
                    leadingIcon = {
                        Icon(Icons.Filled.Search, contentDescription = "Search", tint = Color.Black)
                    },
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            viewModel.newSearch(query)
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
        if (!viewModel.isLoading.value) {
            if (viewModel.hitList.value!!.isNotEmpty()) {
                musicList.value?.let { ShowMusicData(result = it) }
            }
        } else if (viewModel.isLoading.value && viewModel.startSearch.value) {
            CustomCircularProgress(isDisplay = true)
        }
    }
}
