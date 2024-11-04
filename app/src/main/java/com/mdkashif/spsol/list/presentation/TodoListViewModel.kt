package com.mdkashif.spsol.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mdkashif.spsol.shared.model.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class TodoListViewModel: ViewModel() {

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _todoList = MutableStateFlow(listOf(Todo(0, "aljdflajsdfl"), Todo(1, "324143"), Todo(2, ".qer"), Todo(3,"/cz90v8")))
    val todoList =
        query.combine(_todoList) { query, todos ->
            return@combine if (query.isBlank())
                 _todoList.value
            else
                todos.filter { it.doesMatchSearchQuery(query) }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(2000),
            _todoList.value
            )

    fun onQueryTextChange(query: String) {
        _query.value = query
    }
}