package com.example.newsaggregator.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.data.rss.NewsRepositoryImpl
import com.example.newsaggregator.domain.GetAllNews
import com.example.newsaggregator.domain.NewsItem
import com.example.newsaggregator.domain.NewsRepository
import kotlinx.coroutines.launch

class MainVM : ViewModel() {


    private val repository = NewsRepositoryImpl()

    private val getAllNews = GetAllNews(repository)

    private val _newsList = MutableLiveData<List<NewsItem>>()
    val newsList : LiveData<List<NewsItem>> = _newsList

    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean> = _isRefreshing


    fun refreshNews(){
        viewModelScope.launch {
            _isRefreshing.value = true
            _newsList.postValue(getAllNews.getAllNews())
            _isRefreshing.value = false
        }
    }

    fun getAllNews(){
        Log.d("data", "LOADING")
        viewModelScope.launch { _newsList.postValue(getAllNews.getAllNews()) }
    }







}