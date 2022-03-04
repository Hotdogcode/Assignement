package com.test.assignment.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.assignment.data.model.TrendingRepo
import com.test.assignment.data.repository.MainRepo
import com.test.assignment.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(
    val mainRepo: MainRepo
): ViewModel() {
    var preSelectedIndex = -1
    var curSelectedIndex = -1

    var items = ArrayList<TrendingRepo>()
    var matchedItems = ArrayList<TrendingRepo>()
    var listData = ArrayList<TrendingRepo>()
    private val _repos = MutableLiveData<Resource<List<TrendingRepo>>>()
    val repos: LiveData<Resource<List<TrendingRepo>>>
        get() = _repos



    init {

        fetchRepos()
    }

    fun updateData(data:List<TrendingRepo>){
        items.addAll(data)
        _repos.postValue(Resource.success(ArrayList()))
        updateListData(items)
    }

    fun updateMatchedData(data: List<TrendingRepo>){
        matchedItems.clear()
        matchedItems.addAll(data)
        updateListData(matchedItems)
    }

    fun updateListData(data: List<TrendingRepo>){
        preSelectedIndex = -1
        curSelectedIndex = -1
        listData.clear()
        listData.addAll(data)
    }



    private fun fetchRepos(){
        viewModelScope.launch {
            mainRepo.getTrendingRepos().let {
                if (it.isSuccessful) {
                    _repos.postValue(Resource.success(it.body()))
                } else _repos.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }



}