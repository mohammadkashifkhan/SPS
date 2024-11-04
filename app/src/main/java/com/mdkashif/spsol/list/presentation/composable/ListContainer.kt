package com.mdkashif.spsol.list.presentation.composable

import DisplayTodoList
import TodoDetail
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mdkashif.spsol.R
import com.mdkashif.spsol.list.presentation.TodoListViewModel
import com.mdkashif.spsol.shared.composable.AppBar

@Composable
fun ListContainer(navController: NavController, viewModel: TodoListViewModel, error: String = "") {
    val query by viewModel.query.collectAsState()
    val todos by viewModel.todoList.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            AppBar(
                navController,
                stringResource(id = R.string.app_name)
            )
        },
        content = { paddingValues ->
            if (todos.isEmpty())
                Text(modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp), text = stringResource(id = R.string.empty_list_message))
            else
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    TextField(
                        leadingIcon = {
                            Icon(
                                Icons.Filled.Search,
                                null,
                                tint = LocalContentColor.current.copy(alpha = 0.3f)
                            )
                        },
                        value = query,
                        onValueChange = viewModel::onQueryTextChange,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .weight(0.4f),
                        placeholder = { Text(text = stringResource(id = R.string.search)) }
                    )
                    DisplayTodoList(
                        Modifier
                            .padding(paddingValues)
                            .fillMaxSize()
                            .weight(1f),
                        todos
                    )
                    if (error.isNotBlank()) {
                        LaunchedEffect(key1 = error) {
                            snackbarHostState.showSnackbar(error)
                        }
                    }
                }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(TodoDetail)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add"
                )
            }
        }
    )
}