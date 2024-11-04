package com.mdkashif.spsol.shared.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mdkashif.spsol.shared.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM Todo")
    fun getAll(): Flow<List<Todo>>

    @Insert
    fun insert(todo: Todo)
}