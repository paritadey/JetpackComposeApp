package com.parita.jetpackcomposeapp.ui

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.parita.jetpackcomposeapp.util.JetpackConstant.description
import com.parita.jetpackcomposeapp.util.JetpackConstant.title
import java.util.*

@Composable
fun AddNoteScreen(findNavController: NavController) {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            SectionNoteGreeting(ANS1)
            SectionEditBox(title)
            SectionEditBox(description)
            SectionCategory()
            SectionDueDate()
            SectionAddTask()
        }
    }
}

@Composable
fun SectionEditBox(type: String) {
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
            //singleLine = true,
            maxLines = if (type.equals(description)) 50 else 1,
            value = textTitle,
            onValueChange = { textTitle = it },
            label = { Text(stringResource(id = R.string.title)) },
            modifier = if (type.equals(description)) {
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
                focusedBorderColor = White,
                unfocusedBorderColor = White,
                focusedLabelColor = BlueViolet1,
                cursorColor = White
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
                    focusedLabelColor = White,
                    unfocusedLabelColor = White,
                    textColor = White,
                    cursorColor = White,
                    focusedIndicatorColor = White,
                    unfocusedIndicatorColor = White,
                    backgroundColor = Transparent,
                    trailingIconColor = White,
                    focusedTrailingIconColor = White
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
                        Text(text = selectedOption, color = Black)
                    }
                }
            }
        }
    }
}

@Composable
fun SectionDueDate() {
    val localContext = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()
    val date = remember {
        mutableStateOf("")
    }
    val datePickerDialog =
        DatePickerDialog(localContext, { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/$month/$year"
        }, year, month, day)

    val checkedState = remember { mutableStateOf(true) }
    Row {
        Checkbox(
            checked = checkedState.value,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            onCheckedChange = { checkedState.value = it },
            colors = CheckboxDefaults.colors(
                checkedColor = BlueViolet1,
                uncheckedColor = White,
                checkmarkColor = White
            )
        )
        Text(
            text = stringResource(id = R.string.add_due_date),
            modifier = Modifier.padding(end = 16.dp, top = 30.dp)
        )
    }
    if (checkedState.value.equals(true)) {
        Row {
            OutlinedButton(
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Transparent,
                ),
                onClick = {
                    datePickerDialog.show()
                },
                modifier = Modifier.padding(start = 16.dp, top = 8.dp),
            ) {
                Text(stringResource(id = R.string.due_date), color = White)
                Spacer(modifier = Modifier.padding(start = 8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = stringResource(id = R.string.due_date),
                    tint = White,
                    modifier = Modifier.size(16.dp)
                )
            }
            if (date.value != null) Text(
                text = "${date.value}",
                modifier = Modifier.padding(top = 22.dp, start = 8.dp)
            )
        }
    }
}

@Composable
fun alert(
    msg: String,
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            title = {
                Text(text = stringResource(id = R.string.please_confirm), color = Black)
            },
            text = {
                Text(msg, color = Black)
            },
            onDismissRequest = onDismiss,
            dismissButton = {
                TextButton(onClick = onDismiss)
                { Text(text = stringResource(id = R.string.cancel), color = Black) }
            },
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text(stringResource(id = R.string.ok), color = Black)
                }
            },
        )
    }
}

@Composable
fun SectionAddTask() {
    val showDialog = remember {
        mutableStateOf(false)
    }
    if (showDialog.value) {
        alert(
            msg = stringResource(id = R.string.save_note_alert),
            showDialog = showDialog.value,
            onDismiss = { showDialog.value = false })
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.End
    ) {
        OutlinedButton(
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = BlueViolet1,
            ),
            onClick = { showDialog.value = true },
            modifier = Modifier
                .padding(16.dp)
                .width(150.dp)
                .height(50.dp),
        ) {
            Text(stringResource(id = R.string.add_task), color = White)
        }
    }
}