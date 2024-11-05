package com.mdkashif.spsol.features.list.domain

import com.mdkashif.spsol.shared.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoListRepository {
    suspend fun getAll(): Flow<List<Todo>>
}