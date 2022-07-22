package com.parita.jetpackcomposeapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
            shape = RoundedCornerShape(10.dp),
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
fun SectionDescription() {
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
            shape = RoundedCornerShape(10.dp),
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SectionCategory() {
    SectionNoteGreeting(ANS2)
    val options = listOf<String>(
        "Choose Category / Tag",
        "Work",
        "Office",
        "School",
        "Daily Life",
        "Grocery",
        "Event",
        "DateToRemember",
        "Finance",
        "Others"
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember {
        mutableStateOf(options[0])
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            TextField(
                readOnly = true,
                value = selectedOptionText,
                onValueChange = {},
                //label = { Text("Categories") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    textColor = Color.White,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    backgroundColor = Color.Transparent,
                    trailingIconColor = Color.White,
                    focusedTrailingIconColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp
                    )
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                options.forEach { selectedOption ->
                    DropdownMenuItem(onClick = {
                        selectedOptionText = selectedOption
                        expanded = false
                    }) {
                        Text(text = selectedOption, color = Color.Black)
                    }
                }
            }
        }
    }
}