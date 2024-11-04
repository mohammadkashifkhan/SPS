package com.mdkashif.spsol.detail.domain

import com.mdkashif.spsol.shared.model.Todo

interface TodoDetailRepository {
    fun insert(todo: Todo)
}