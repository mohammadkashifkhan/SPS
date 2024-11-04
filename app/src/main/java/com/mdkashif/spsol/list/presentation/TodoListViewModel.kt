package com.mdkashif.spsol.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mdkashif.spsol.list.data.TodoListRepositoryImpl
import com.mdkashif.spsol.list.domain.TodoListRepository
import com.mdkashif.spsol.shared.model.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoListViewModel(private val repository: TodoListRepository) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    init {
        _getAllTodos()
    }

    private val _todoList = MutableStateFlow(emptyList<Todo>())
    val todoList =
        query.combine(_todoList) { query, todos ->
            return@combine if (query.isBlank())
                _todoList.value
            else
                todos.filter { it.doesMatchSearchQuery(query) }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(20000),
            _todoList.value
        )

    fun onQueryTextChange(query: String) {
        _query.value = query
    }

    private fun _getAllTodos() {
        viewModelScope.launch {
            repository.getAll().collect {
                _todoList.value = it
            }
        }
    }
}