package com.mdkashif.spsol.shared.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mdkashif.spsol.shared.model.Todo

@Database(entities = [Todo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun initTodoDao(): TodoDao
}