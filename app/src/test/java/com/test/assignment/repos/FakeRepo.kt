package com.test.assignment.repos

import com.test.assignment.data.model.TrendingRepo
import com.test.assignment.data.repository.IMainRepo

class FakeRepo(private val fakeApiHelper: FakeApiHelper):IMainRepo {
    override suspend fun getTrendingRepos() =  fakeApiHelper.getTrendingRepos()
}