package com.mdkashif.spsol.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mdkashif.spsol.detail.data.TodoDetailRepositoryImpl
import com.mdkashif.spsol.detail.domain.TodoDetailRepository
import com.mdkashif.spsol.shared.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoDetailViewModel(private val repository: TodoDetailRepository) : ViewModel() {
    private val emptyTodo = Todo(note = "")
    private val _todo = MutableStateFlow(emptyTodo)
    val todo = _todo.asStateFlow()

    fun onTodoNoteChange(todo: String) {
        _todo.value = Todo(note = todo)
    }

    fun addTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(todo)
            _todo.value = emptyTodo
        }
    }
}