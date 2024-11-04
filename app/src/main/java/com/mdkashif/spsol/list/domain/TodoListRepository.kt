package com.mdkashif.spsol.list.domain

import com.mdkashif.spsol.shared.model.Todo

interface TodoListRepository {
    fun getAll(): List<Todo>

    fun findByQuery(query: String): List<Todo>
}