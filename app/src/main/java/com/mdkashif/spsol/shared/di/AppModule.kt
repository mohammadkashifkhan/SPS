package com.mdkashif.spsol.shared.di

import androidx.room.Room
import com.mdkashif.spsol.detail.data.TodoDetailRepositoryImpl
import com.mdkashif.spsol.detail.domain.TodoDetailRepository
import com.mdkashif.spsol.detail.presentation.TodoDetailViewModel
import com.mdkashif.spsol.list.data.TodoListRepositoryImpl
import com.mdkashif.spsol.list.domain.TodoListRepository
import com.mdkashif.spsol.list.presentation.TodoListViewModel
import com.mdkashif.spsol.shared.utils.Constants
import com.mdkashif.spsol.shared.db.AppDatabase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            databaseModule,
            repositoryModule,
            viewModelModule
        )
    )
}

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, Constants.dbAlias)
            .build()
    }

    single { get<AppDatabase>().initTodoDao() }
}

val repositoryModule = module {
    single<TodoListRepository> { TodoListRepositoryImpl(todoDao = get()) }
    single<TodoDetailRepository> { TodoDetailRepositoryImpl(todoDao = get()) }
}

val viewModelModule = module {
    viewModel { TodoListViewModel(repository = get()) }
    viewModel { TodoDetailViewModel(repository = get()) }
}