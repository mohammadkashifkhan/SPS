package com.mdkashif.spsol.features.list.presentation.composable

import DisplayTodoList
import TodoDetail
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mdkashif.spsol.R
import com.mdkashif.spsol.features.list.presentation.TodoListViewModel
import com.mdkashif.spsol.shared.composable.AppBar
import com.mdkashif.spsol.shared.utils.Constants

@Composable
fun ListContainer(
    navController: NavController,
    viewModel: TodoListViewModel,
    error: String = Constants.empty
) {
    val query by viewModel.query.collectAsState()
    val todos by viewModel.todoList.collectAsState()
    val showEmptyBanner by viewModel.showEmptyBanner.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            AppBar(
                navController,
                null,
                stringResource(id = R.string.app_name)
            )
        },
        content = { paddingValues ->
            Surface(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
                if (showEmptyBanner)
                    Text(
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = true
                            )
                        ),
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentHeight(align = Alignment.CenterVertically)
                            .size(16.dp),
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.empty_list_message)
                    )
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
                                    tint = LocalContentColor.current
                                )
                            },
                            value = query,
                            onValueChange = viewModel::onQueryTextChange,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            placeholder = { Text(text = stringResource(id = R.string.search)) }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        DisplayTodoList(
                            Modifier
                                .fillMaxSize(),
                            todos
                        )
                        if (error.isNotBlank()) {
                            LaunchedEffect(key1 = error) {
                                snackbarHostState.showSnackbar(error)
                            }
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