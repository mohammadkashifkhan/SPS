package com.mdkashif.spsol

import DisplayTodoList
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mdkashif.spsol.list.presentation.TodoListViewModel
import com.mdkashif.spsol.shared.Constants
import com.mdkashif.spsol.shared.composable.CenterAlignedAppBar
import com.mdkashif.spsol.ui.theme.SPSolTheme
import kotlinx.serialization.Serializable
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: TodoListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SPSolTheme {
                val navController = rememberNavController()
                val query by viewModel.query.collectAsState()
                val todos by viewModel.todoList.collectAsState()
                
                NavHost(navController = navController, startDestination = TodoList) {
                    composable<TodoList> {
                        Scaffold(
                            topBar = { CenterAlignedAppBar() },
                            content = { paddingValues ->
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
                                        placeholder = { Text(text = Constants.search) }
                                    )
                                    DisplayTodoList(
                                        Modifier
                                            .padding(paddingValues)
                                            .fillMaxSize()
                                            .weight(1f),
                                        todos
                                    )
                                }
                            },
                            floatingActionButton = {
                                FloatingActionButton(onClick = {
                                    navController.navigate(TodoDetail)
                                }) {
                                    Icon(imageVector = Icons.Default.Add, contentDescription = "add")
                                }
                            })
                    }
                    composable<TodoDetail> {
                        Scaffold(
                            topBar = { CenterAlignedAppBar() },
                            content = { paddingValues ->
                                Text(modifier = Modifier.padding(paddingValues), text = "Detail here...")
                            }
                        )
                    }
                }
            }
        }
    }

    @Serializable
    object TodoList

    @Serializable
    object TodoDetail
}