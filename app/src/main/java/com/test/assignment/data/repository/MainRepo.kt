package com.test.assignment.data.repository

import com.test.assignment.data.api.ApiHelper

class MainRepo (private val apiHelper: ApiHelper) : IMainRepo{

    override suspend fun getTrendingRepos() =  apiHelper.getTrendingRepos()

}