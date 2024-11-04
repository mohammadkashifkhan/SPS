package com.mdkashif.spsol

import DisplayTodoList
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mdkashif.spsol.detail.presentation.DetailContainer
import com.mdkashif.spsol.detail.presentation.TodoDetailViewModel
import com.mdkashif.spsol.list.presentation.TodoListViewModel
import com.mdkashif.spsol.list.presentation.composable.ListContainer
import com.mdkashif.spsol.shared.Constants
import com.mdkashif.spsol.shared.composable.CenterAlignedAppBar
import com.mdkashif.spsol.ui.theme.SPSolTheme
import kotlinx.serialization.Serializable
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
                    composable<TodoList> {
                        ListContainer(navController = navController, viewModel = listViewModel)
                    }
                    composable<TodoDetail> {
                        DetailContainer(navController = navController, viewModel = detailViewModel)
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