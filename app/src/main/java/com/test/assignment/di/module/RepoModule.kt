package com.test.assignment.di.module

import com.test.assignment.data.repository.MainRepo
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepo(get())
    }
}