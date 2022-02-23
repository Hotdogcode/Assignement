package com.test.assignment.di.module

import com.test.assignment.ui.main.viewmodel.MainViewModel
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel
val viewModelModule = module {
    viewModel {
        MainViewModel(get(),get())
    }
}