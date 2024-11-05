package com.mdkashif.spsol.list.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mdkashif.spsol.shared.model.Todo

@Composable
fun TodoItem(item: Todo, index: Int) {
    Column{
        Row(
            modifier = Modifier.height(IntrinsicSize.Max)
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                fontSize = 16.sp,
                text = "$index. ${item.note}"
            )
        }
    }
}