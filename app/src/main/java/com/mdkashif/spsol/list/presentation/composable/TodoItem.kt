package com.mdkashif.spsol.list.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mdkashif.spsol.shared.model.Todo

@Composable
fun TodoItem(item: Todo) {
    val clickableState = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.clickable { clickableState.value = clickableState.value.not() }
    ) {
        Row(
            modifier = Modifier.height(IntrinsicSize.Max)
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 24.dp, top = 16.dp),
                fontSize = 16.sp,
                text = item.note ?: "",
            )
        }
    }
}