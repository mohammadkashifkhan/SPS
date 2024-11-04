package com.mdkashif.spsol.list.presentation

import androidx.lifecycle.ViewModel

class TodoListViewModel: ViewModel() {

    private val _todoList = listOf("aljdflajsdfl", "324143", ".qer", "/cz90v8")

    fun getTodoList(): List<String> {
        return _todoList
    }
}