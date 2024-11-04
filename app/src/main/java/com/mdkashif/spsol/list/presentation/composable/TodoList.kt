import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mdkashif.spsol.list.presentation.composable.TodoItem

@Composable
fun DisplayTodoList(paddingValues: PaddingValues, directory: List<String>) {
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        directory.forEach { directoryItem ->
            items(
                items = directory,
                itemContent = { TodoItem(it) }
            )
        }
    }
}