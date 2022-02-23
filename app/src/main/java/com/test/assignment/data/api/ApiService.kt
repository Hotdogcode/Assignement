package com.test.assignment.data.api

import com.test.assignment.data.model.TrendingRepo
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getTrendingRepos(): Response<List<TrendingRepo>>

}