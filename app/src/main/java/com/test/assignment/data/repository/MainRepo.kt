package com.test.assignment.data.repository

import com.test.assignment.data.api.ApiHelper

class MainRepo (private val apiHelper: ApiHelper) {

    suspend fun getTrendingRepos() =  apiHelper.getTrendingRepos()

}