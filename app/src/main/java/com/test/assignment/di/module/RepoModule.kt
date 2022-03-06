package com.test.assignment.di.module

import com.test.assignment.data.repository.IMainRepo
import com.test.assignment.data.repository.MainRepo
import org.koin.dsl.binds
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepo(get())
    }binds arrayOf(IMainRepo::class)
}