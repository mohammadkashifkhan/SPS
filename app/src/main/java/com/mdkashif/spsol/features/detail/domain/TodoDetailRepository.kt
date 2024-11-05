package com.mdkashif.spsol.features.detail.domain

import com.mdkashif.spsol.shared.model.Todo

interface TodoDetailRepository {
    suspend fun insert(todo: Todo)
}