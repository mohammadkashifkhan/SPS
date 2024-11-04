package com.mdkashif.spsol

import TodoDetail
import TodoList
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mdkashif.spsol.detail.presentation.DetailContainer
import com.mdkashif.spsol.detail.presentation.TodoDetailViewModel
import com.mdkashif.spsol.list.presentation.TodoListViewModel
import com.mdkashif.spsol.list.presentation.composable.ListContainer
import com.mdkashif.spsol.shared.utils.Constants
import com.mdkashif.spsol.ui.theme.SPSolTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val listViewModel: TodoListViewModel by viewModel()
    private val detailViewModel: TodoDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SPSolTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = TodoList) {
                    composable<TodoList> { entry ->
                        ListContainer(navController = navController, viewModel = listViewModel, entry.savedStateHandle.get(
                            Constants.error) ?: "")
                    }
                    composable<TodoDetail> {
                        DetailContainer(navController = navController, viewModel = detailViewModel)
                    }
                }
            }
        }
    }
}