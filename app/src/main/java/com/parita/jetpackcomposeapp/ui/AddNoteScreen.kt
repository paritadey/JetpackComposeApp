package com.parita.jetpackcomposeapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.parita.jetpackcomposeapp.R
import com.parita.jetpackcomposeapp.ui.theme.BlueViolet1
import com.parita.jetpackcomposeapp.ui.theme.DeepBlue
import com.parita.jetpackcomposeapp.util.JetpackConstant.ANS1
import com.parita.jetpackcomposeapp.util.JetpackConstant.ANS2

@Composable
fun AddNoteScreen(findNavController: NavController) {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            SectionNoteGreeting(ANS1)
            SectionTitle()
            SectionDescription()
            SectionCategory()
        }
    }
}

@Composable
fun SectionTitle() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        var textTitle by remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            value = textTitle,
            onValueChange = { textTitle = it },
            label = { Text("Title") },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                focusedLabelColor = BlueViolet1,
                cursorColor = Color.White
            )
        )
    }
}

@Composable
fun SectionDescription(){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        var textTitle by remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            maxLines = 50,
            value = textTitle,
            onValueChange = { textTitle = it },
            label = { Text("Description") },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                .fillMaxWidth()
                .height(200.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                focusedLabelColor = BlueViolet1,
                cursorColor = Color.White
            )
        )
    }
}

@Composable
fun SectionCategory(){
    SectionNoteGreeting(ANS2)
}