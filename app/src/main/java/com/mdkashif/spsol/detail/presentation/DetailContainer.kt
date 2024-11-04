package com.mdkashif.spsol.detail.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mdkashif.spsol.R
import com.mdkashif.spsol.shared.composable.AppBar
import com.mdkashif.spsol.shared.utils.Constants

@Composable
fun DetailContainer(navController: NavController, viewModel: TodoDetailViewModel) {
    val todo by viewModel.todo.collectAsState()
    Scaffold(
        topBar = { AppBar(navController, "Add Todo") },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                TextField(
                    value = todo.note,
                    onValueChange = viewModel::onTodoNoteChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.8f),
                    placeholder = { Text(text = stringResource(id = R.string.start_typing)) }
                )
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .weight(0.2f),
                    onClick = {
                        if (todo.note.isNotBlank()) {
                            if(todo.note == Constants.error) {
                                navController.previousBackStackEntry?.
                                    savedStateHandle?.set(Constants.error, Constants.popback_error)
                            }
                            else {
                                viewModel.addTodo(todo)
                            }
                            navController.popBackStack()
                        }
                    }) {
                    Text(text = stringResource(id = R.string.add_note))
                }
            }
        }
    )
}