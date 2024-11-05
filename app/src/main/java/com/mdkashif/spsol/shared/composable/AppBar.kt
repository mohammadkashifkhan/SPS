package com.mdkashif.spsol.shared.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.mdkashif.spsol.features.detail.presentation.TodoDetailViewModel
import com.mdkashif.spsol.shared.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(navController: NavController, viewModel: TodoDetailViewModel?, title: String) {
    TopAppBar(
        navigationIcon = {
            if (navController.previousBackStackEntry != null) {
                IconButton(onClick = {
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        Constants.error,
                        ""
                    )
                    navController.navigateUp()
                    viewModel?.clearStates()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        title = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
                Text(
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.ExtraBold,
                    text = title
                )
            }
        }
    )
}