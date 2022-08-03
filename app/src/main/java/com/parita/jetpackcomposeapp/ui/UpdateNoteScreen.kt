package com.parita.jetpackcomposeapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.parita.jetpackcomposeapp.R
import com.parita.jetpackcomposeapp.data.NotesData
import com.parita.jetpackcomposeapp.ui.theme.BlueViolet1
import com.parita.jetpackcomposeapp.ui.theme.DeepBlue
import com.parita.jetpackcomposeapp.util.JetpackConstant
import com.parita.jetpackcomposeapp.util.JetpackConstant.description
import com.parita.jetpackcomposeapp.util.JetpackConstant.title
import com.parita.jetpackcomposeapp.viewmodel.JetpackViewModel

@Composable
fun UpdateNoteScreen(findNavController: NavController, notesData: NotesData) {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            val viewModel: JetpackViewModel = hiltViewModel()
            SectionNoteGreeting(JetpackConstant.UNS)
            SectionBox(type = title, viewModel = viewModel, notesData = notesData)
            SectionBox(type = description, viewModel = viewModel, notesData = notesData)
        }
    }
}

@Composable
fun SectionBox(type: String, viewModel: JetpackViewModel, notesData: NotesData) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        var textData by remember {
            if(type.equals(JetpackConstant.title))
            mutableStateOf(notesData.noteTitle)
            else if(type.equals(JetpackConstant.description)) mutableStateOf(notesData.noteDescription)
            else mutableStateOf("")
        }
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(10.dp),
            //singleLine = true,
            maxLines = if (type.equals(JetpackConstant.description)) 50 else 1,
            value = textData,
            onValueChange = { textData = it },
            label = {
                if (type.equals(JetpackConstant.title)) Text(stringResource(id = R.string.title)) else Text(
                    stringResource(id = R.string.description)
                )
            },
            modifier = if (type.equals(JetpackConstant.description)) {
                Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                    .fillMaxWidth()
                    .height(200.dp)
            } else {
                Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                    .fillMaxWidth()
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                focusedLabelColor = BlueViolet1,
                cursorColor = Color.White
            )
        )
        when {
            textData != "" && textData != null -> {
                when {
                    type.equals(JetpackConstant.title) -> {
                        viewModel.onTitleChanged(textData)
                    }
                    type.equals(JetpackConstant.description) -> {
                        viewModel.onDescriptionChanged(textData)
                    }
                }
            }
        }
    }
}
