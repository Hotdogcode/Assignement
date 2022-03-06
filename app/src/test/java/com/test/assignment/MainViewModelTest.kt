package com.test.assignment

import com.test.assignment.data.model.TrendingRepo
import com.test.assignment.repos.FakeApiHelper
import com.test.assignment.repos.FakeRepo
import com.test.assignment.ui.main.viewmodel.MainViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {
    private lateinit var mainViewModel : MainViewModel
    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatureRule()

    @Before
    fun setUp(){
        mainViewModel = MainViewModel(FakeRepo(FakeApiHelper()))
        Dispatchers.setMain(kotlinx.coroutines.test.StandardTestDispatcher())
    }

    @Test
    fun `fetch repos successfully, return response list`() = runTest {
        mainViewModel.fetchRepos()
        assertEquals(mutableListOf<TrendingRepo>(),mainViewModel.listData)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}