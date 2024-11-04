package com.mdkashif.spsol.shared.di

import androidx.room.Room
import com.mdkashif.spsol.detail.data.TodoDetailRepositoryImpl
import com.mdkashif.spsol.detail.domain.TodoDetailRepository
import com.mdkashif.spsol.detail.presentation.TodoDetailViewModel
import com.mdkashif.spsol.list.data.TodoListRepositoryImpl
import com.mdkashif.spsol.list.domain.TodoListRepository
import com.mdkashif.spsol.list.presentation.TodoListViewModel
import com.mdkashif.spsol.shared.Constants
import com.mdkashif.spsol.shared.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    single { androidApplication().applicationContext }

    singleOf(::TodoListRepositoryImpl) { bind<TodoListRepository>() }
    singleOf(::TodoDetailRepositoryImpl) { bind<TodoDetailRepository>() }

    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, Constants.dbAlias)
            .build()
    }

    single { get<AppDatabase>().initTodoDao() }

    viewModelOf(::TodoListViewModel)
    viewModelOf(::TodoDetailViewModel)
}