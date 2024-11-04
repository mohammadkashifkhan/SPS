package com.mdkashif.spsol.list.data

import com.mdkashif.spsol.list.domain.TodoListRepository
import com.mdkashif.spsol.shared.model.Todo

class TodoListRepositoryImpl: TodoListRepository {

    override fun getAll(): List<Todo> {
        TODO("Not yet implemented")
    }

    override fun findByQuery(query: String): List<Todo> {
        TODO("Not yet implemented")
    }
}