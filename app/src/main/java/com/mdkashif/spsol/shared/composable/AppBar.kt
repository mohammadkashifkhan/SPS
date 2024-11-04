package com.mdkashif.spsol.shared.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.mdkashif.spsol.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAlignedAppBar() {
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.ExtraBold,
                    text = stringResource(id = R.string.app_name)
                )
            }
        }
    )
}