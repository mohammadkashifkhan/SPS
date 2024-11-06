package com.mdkashif.spsol.features.detail.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mdkashif.spsol.R
import com.mdkashif.spsol.features.detail.presentation.TodoDetailViewModel
import com.mdkashif.spsol.shared.composable.AppBar
import com.mdkashif.spsol.shared.utils.Constants

@Composable
fun DetailContainer(navController: NavController, viewModel: TodoDetailViewModel) {
    val todo by viewModel.todo.collectAsState()
    val showLoader by viewModel.isLoading.collectAsState()
    val noteAdded by viewModel.noteAdded.collectAsState()
    Scaffold(
        topBar = { AppBar(navController, viewModel, stringResource(id = R.string.enter_new_item)) },
        content = { paddingValues ->
            Surface(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
                ShowLoaderAndPopBackStack(viewModel, showLoader, noteAdded, navController)

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    TextField(
                        maxLines = 1,
                        singleLine = true,
                        value = todo.note,
                        onValueChange = viewModel::onTodoNoteChange,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        placeholder = { Text(text = stringResource(id = R.string.start_typing)) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .wrapContentSize(),
                        enabled = !showLoader,
                        onClick = {
                            if (todo.note.isNotBlank()) {
                                if (todo.note == Constants.error) {
                                    navController.previousBackStackEntry?.savedStateHandle?.set(
                                        Constants.error,
                                        Constants.popback_error
                                    )
                                    viewModel.clearStates()
                                    navController.navigateUp()
                                } else {
                                    navController.previousBackStackEntry?.savedStateHandle?.set(
                                        Constants.error,
                                        Constants.empty
                                    )
                                    viewModel.addTodo(todo)
                                }
                            }
                        }) {
                        Text(text = stringResource(id = R.string.add_note))
                    }
                }
            }
        }
    )
}