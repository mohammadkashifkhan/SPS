package com.mdkashif.spsol.features.list.data

import com.mdkashif.spsol.features.list.domain.TodoListRepository
import com.mdkashif.spsol.shared.db.TodoDao
import com.mdkashif.spsol.shared.model.Todo
import kotlinx.coroutines.flow.Flow

class TodoListRepositoryImpl(private val todoDao: TodoDao): TodoListRepository {

    override suspend fun getAll(): Flow<List<Todo>> {
        return todoDao.getAll()
    }
}