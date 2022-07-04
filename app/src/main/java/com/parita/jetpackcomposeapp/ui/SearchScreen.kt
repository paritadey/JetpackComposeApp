package com.parita.jetpackcomposeapp.ui

import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.navigation.NavController
import com.parita.jetpackcomposeapp.R
import com.parita.jetpackcomposeapp.data.Track
import com.parita.jetpackcomposeapp.itemDecoration.MusicRow
import com.parita.jetpackcomposeapp.ui.theme.DeepBlue
import com.parita.jetpackcomposeapp.viewmodel.JetpackViewModel

@Composable
fun SearchScreen(findNavController: NavController) {
    Scaffold(backgroundColor = DeepBlue) {
        SearchView(findNavController)
    }
}

@Composable
fun SearchView(findNavController: NavController) {
    val viewModel: JetpackViewModel = hiltViewModel()
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
                musicList.value?.let { MusicList(result = it, findNavController) }
            }
        } else if (viewModel.isLoading.value && viewModel.startSearch.value) {
            CustomCircularProgress(isDisplay = true)
        }
    }
}

@Composable
fun MusicList(result: ArrayList<Track>, findNavController: NavController){
    LazyColumn(contentPadding = PaddingValues(12.dp)){
        items(result){ data->
            MusicRow(track = data, selectedItem = {sendData(it, findNavController)})
        }
    }
}

fun sendData(data:Track, findNavController: NavController){
    var bundle = Bundle()
    bundle.putString("key", data.key)
    bundle.putString("title", data.title)
    bundle.putString("subtitle", data.subtitle)
    bundle.putParcelable("share", data.share)
    bundle.putParcelable("image", data.images)
    findNavController.navigate(R.id.SearchToPlayMusic, bundle)
}

