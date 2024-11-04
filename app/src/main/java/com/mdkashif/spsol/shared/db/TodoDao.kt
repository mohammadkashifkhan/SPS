package com.mdkashif.spsol.shared.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mdkashif.spsol.shared.model.Todo

@Dao
interface TodoDao {

    @Query("SELECT * FROM Todo")
    fun getAll(): List<Todo>

    @Query("SELECT * FROM todo WHERE note LIKE :query")
    fun findByQuery(query: String): List<Todo>

    @Insert
    fun insert(todo: Todo)
}