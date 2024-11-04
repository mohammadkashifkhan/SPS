package com.mdkashif.spsol.detail.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mdkashif.spsol.shared.Constants
import com.mdkashif.spsol.shared.composable.CenterAlignedAppBar

@Composable
fun DetailContainer(navController: NavController, viewModel: TodoDetailViewModel) {
    val todo by viewModel.todo.collectAsState()
    Scaffold(
        topBar = { CenterAlignedAppBar(navController, "Add Todo") },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                TextField(
                    leadingIcon = {
                        Icon(
                            Icons.Filled.MailOutline,
                            null,
                            tint = LocalContentColor.current.copy(alpha = 0.3f)
                        )
                    },
                    value = todo.note,
                    onValueChange = viewModel::onTodoNoteChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.8f),
                    placeholder = { Text(text = Constants.startTyping) }
                )
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .weight(0.2f),
                    onClick = { viewModel.addTodo(todo) }) {
                    Text(text = Constants.addNote)
                }
            }
        }
    )
}