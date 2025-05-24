package com.example.newsaggregator.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.data.repository.NewsRepositoryImpl
import com.example.newsaggregator.domain.usecases.GetAllNews
import com.example.newsaggregator.domain.usecases.GetNewsWithCategory
import com.example.newsaggregator.domain.entity.NewsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainVM(application: Application) : AndroidViewModel(application) {


    private val repository = NewsRepositoryImpl(context = application)

    private val getAllNews = GetAllNews(repository)
    private val getNewsWithCategory = GetNewsWithCategory(repository)

    private val _newsList = MutableLiveData<List<NewsItem>>()
    val newsList : LiveData<List<NewsItem>> = _newsList

    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    private val _allCategorys = MutableLiveData<List<String>>()
    val allCategorys : LiveData<List<String>> = _allCategorys



    fun refreshNews(){
        viewModelScope.launch {
            _isRefreshing.value = true
            withContext(Dispatchers.IO){_newsList.postValue(getAllNews.getAllNews())}
            _isRefreshing.value = false
            getAllCategories()
        }

    }


    fun getAllNews(){
        Log.d("data", "LOADING")
        viewModelScope.launch {
            val news = withContext(Dispatchers.IO) {
                getAllNews.getAllNews()
            }
            _newsList.value = news
            Log.d("category", "${_newsList.value}")
            getAllCategories()
        }}

    fun getNewsWithCategory(category : String){
        Log.d("category", "LOADING")
        viewModelScope.launch { _newsList.postValue(getNewsWithCategory.getNewsWithCategory(category)) }
    }

    suspend fun getAllCategories(){
        _allCategorys.postValue(
            _newsList.value
                ?.mapNotNull { it.category.takeIf { cat -> cat.isNotBlank() } }
                ?.distinct()
                ?.sorted()
        )

    }







}