package com.test.assignment.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.assignment.data.model.TrendingRepo
import com.test.assignment.data.repository.MainRepo
import com.test.assignment.utils.NetworkHelper
import com.test.assignment.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepo: MainRepo,
    private val networkHelper: NetworkHelper
): ViewModel() {
    private val _repos = MutableLiveData<Resource<List<TrendingRepo>>>()
    val repos: LiveData<Resource<List<TrendingRepo>>>
        get() = _repos

    init {
        fetchRepos()
    }

    fun fetchRepos(){
        viewModelScope.launch {
            _repos.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepo.getTrendingRepos().let {
                    if (it.isSuccessful) {
                        _repos.postValue(Resource.success(it.body()))
                    } else _repos.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _repos.postValue(Resource.error("No internet connection", null))
        }
    }

}