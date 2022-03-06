package com.test.assignment.repos

import com.test.assignment.data.api.ApiHelper
import com.test.assignment.data.model.TrendingRepo
import retrofit2.Response

class FakeApiHelper : ApiHelper {

    private val itemResponse : Response<List<TrendingRepo>> = Response.success(mutableListOf())
    private var shouldReturnError = false

    fun shouldReturnNetworkError(value:Boolean){
        shouldReturnError = value
    }

    override suspend fun getTrendingRepos(): Response<List<TrendingRepo>> {
        return if(shouldReturnError){
            Response.error(null,null)
        }else {
            itemResponse
        }
    }
}