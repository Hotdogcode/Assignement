package com.test.assignment.data.api

import com.test.assignment.data.model.TrendingRepo
import retrofit2.Response

interface ApiHelper {
    suspend fun getTrendingRepos(): Response<List<TrendingRepo>>
}