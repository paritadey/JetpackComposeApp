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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.parita.jetpackcomposeapp.R
import com.parita.jetpackcomposeapp.data.NotesData
import com.parita.jetpackcomposeapp.ui.theme.BlueViolet1
import com.parita.jetpackcomposeapp.ui.theme.DeepBlue
import com.parita.jetpackcomposeapp.util.JetpackConstant.ANS1
import com.parita.jetpackcomposeapp.util.JetpackConstant.ANS2
import com.parita.jetpackcomposeapp.util.JetpackConstant.description
import com.parita.jetpackcomposeapp.util.JetpackConstant.title
import com.parita.jetpackcomposeapp.viewmodel.JetpackViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@Composable
fun AddNoteScreen(findNavController: NavController, noteArrayList: ArrayList<NotesData>) {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            val viewModel: JetpackViewModel = hiltViewModel()
            SectionNoteGreeting(ANS1)
            SectionEditBox(title, viewModel)
            SectionEditBox(description, viewModel)
            SectionCategory(viewModel)
            SectionDueDate(viewModel)
            SectionAddTask(viewModel, noteArrayList, findNavController)
        }
    }
}

@Composable
fun SectionEditBox(type: String, viewModel: JetpackViewModel) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        var textData by remember {
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
            value = textData,
            onValueChange = { textData = it },
            label = {
                if (type.equals(title)) Text(stringResource(id = R.string.title)) else Text(
                    stringResource(id = R.string.description)
                )
            },
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
        when {
            textData != "" && textData != null -> {
                when {
                    type.equals(title) -> {
                        viewModel.onTitleChanged(textData)
                    }
                    type.equals(description) -> {
                        viewModel.onDescriptionChanged(textData)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SectionCategory(viewModel: JetpackViewModel) {
    SectionNoteGreeting(ANS2)
    val options = listOf(
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
                        viewModel.onCategoryChanged(selectedOption)
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
fun SectionDueDate(viewModel: JetpackViewModel) {
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
            if (date.value != null) {
                viewModel.onDueDateChanged(date.value)
                Text(
                    text = date.value,
                    modifier = Modifier.padding(top = 22.dp, start = 8.dp)
                )
            }
        }
    } else {
        viewModel.onDueDateChanged("NA/NA/NA")
    }
}

@Composable
fun alert(
    msg: String,
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    viewModel: JetpackViewModel,
    noteArrayList: ArrayList<NotesData>, findNavController: NavController
) {
    val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy 'at' HH:mm")
    val currentDateAndTime: String = simpleDateFormat.format(Date())
    viewModel.onCreationTime(currentDateAndTime)
    viewModel.lastNoteData(noteArrayList.last().noteId)

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
                TextButton(onClick = onConfirm) {
                    Text(stringResource(id = R.string.ok), color = Black)
                }
            },
        )
    }
}

@Composable
fun SectionAddTask(viewModel: JetpackViewModel, noteArrayList: ArrayList<NotesData>, findNavController: NavController) {
    val showDialogState: Boolean by viewModel.showDialog.collectAsState()

    if (showDialogState) {
        alert(
            msg = stringResource(id = R.string.save_note_alert),
            showDialog = showDialogState,
            onDismiss = viewModel::onDialogDismiss,
            onConfirm = viewModel::onDialogConfirm,
            viewModel, noteArrayList,
            findNavController
        )
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
            onClick = { viewModel.onOpenDialogClicked()},
            modifier = Modifier
                .padding(16.dp)
                .width(150.dp)
                .height(50.dp),
        ) {
            Text(stringResource(id = R.string.add_task), color = White)
        }
    }
}