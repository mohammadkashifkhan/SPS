import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mdkashif.spsol.list.presentation.composable.TodoItem
import com.mdkashif.spsol.shared.model.Todo

@Composable
fun DisplayTodoList(modifier: Modifier, todos: List<Todo>) {
    LazyColumn(modifier = modifier) {
        todos.forEach { todoItem ->
            items(
                items = todos,
                itemContent = { TodoItem(it) }
            )
        }
    }
}