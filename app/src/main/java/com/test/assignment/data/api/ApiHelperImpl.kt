package com.test.assignment.data.api

import com.test.assignment.data.model.TrendingRepo
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getTrendingRepos(): Response<List<TrendingRepo>> = apiService.getTrendingRepos()
}