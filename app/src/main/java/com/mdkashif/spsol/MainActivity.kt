package com.mdkashif.spsol

import DisplayTodoList
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import com.mdkashif.spsol.list.presentation.TodoListViewModel
import com.mdkashif.spsol.shared.composable.CenterAlignedAppBar
import com.mdkashif.spsol.ui.theme.SPSolTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: TodoListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SPSolTheme {
                Scaffold(topBar = { CenterAlignedAppBar() }, content = { paddingValues ->
                    DisplayTodoList(
                        paddingValues,
                        viewModel.getTodoList()
                    )
                })
            }
        }
    }
}