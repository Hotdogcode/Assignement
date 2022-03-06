package com.test.assignment.data.repository

import com.test.assignment.data.model.TrendingRepo
import retrofit2.Response

interface IMainRepo {
    suspend fun getTrendingRepos() :Response<List<TrendingRepo>>
}