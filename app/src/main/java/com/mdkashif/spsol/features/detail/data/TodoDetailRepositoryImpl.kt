package com.mdkashif.spsol.features.detail.data

import com.mdkashif.spsol.features.detail.domain.TodoDetailRepository
import com.mdkashif.spsol.shared.db.TodoDao
import com.mdkashif.spsol.shared.model.Todo

class TodoDetailRepositoryImpl(private val todoDao: TodoDao) : TodoDetailRepository {

    override suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }
}