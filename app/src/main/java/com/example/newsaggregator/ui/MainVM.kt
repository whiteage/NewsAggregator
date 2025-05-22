package com.example.newsaggregator.ui

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

    fun getAllNews(){
        viewModelScope.launch { _newsList.postValue(getAllNews.getAllNews()) }
    }







}