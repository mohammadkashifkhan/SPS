package com.mdkashif.spsol

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.mdkashif.spsol.shared.db.AppDatabase
import com.mdkashif.spsol.shared.db.TodoDao
import com.mdkashif.spsol.shared.model.Todo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class TodoDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var todoDao: TodoDao

    private val todoFirst = Todo(1, "Testing 1")
    private val todoSecond = Todo(2, "Testing 2")
    private val todoThird = Todo(3, "Testing 3")
    private val todoFourth = Todo(4, "Testing 4")

    @Before
    fun setUpDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), AppDatabase::class.java
        ).allowMainThreadQueries().build()

        todoDao = database.initTodoDao()
    }

    @Test
    fun `test todo dao queries`() = runTest {
        // Insert
        val expected = listOf(todoFirst, todoSecond, todoThird, todoFourth)
        todoDao.insert(todoFirst)
        todoDao.insert(todoSecond)
        todoDao.insert(todoThird)
        todoDao.insert(todoFourth)

        // Retrieve
        val actual = todoDao.getAll().first()
        assertNotNull(actual)
        assertEquals(actual, expected)
    }

    @After
    fun closeDatabase() {
        database.close()
    }
}