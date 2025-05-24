package com.example.newsaggregator.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.domain.usecases.GetAllNews
import com.example.newsaggregator.domain.usecases.GetNewsWithCategory
import com.example.newsaggregator.domain.entity.NewsItem
import com.example.newsaggregator.domain.usecases.PrepareShareContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val getAllNews: GetAllNews,
    private val getNewsWithCategory: GetNewsWithCategory,
    private val prepareShareContent: PrepareShareContent,
    application: Application) : AndroidViewModel(application) {



    private val _newsList = MutableLiveData<List<NewsItem>>()
    val newsList : LiveData<List<NewsItem>> = _newsList

    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    private val _allCategorys = MutableLiveData<List<String>>()
    val allCategorys : LiveData<List<String>> = _allCategorys

    private val _errorState = MutableLiveData<Boolean>(false)
    val errorState : LiveData<Boolean> = _errorState

    private val _shareContent = MutableLiveData<String>()
    val shareContent: LiveData<String> = _shareContent

    private val _chosenCategory = MutableLiveData<String>("All")
    val chosenCategory: LiveData<String> = _chosenCategory

    fun shareContent(url : String){
        val content = prepareShareContent.prepareShareContent(url = url)
        _shareContent.value = content
    }

    fun refreshNews(){
        viewModelScope.launch {
            _isRefreshing.value = true
            if(_chosenCategory.value == "All"){
                withContext(Dispatchers.IO){_newsList.postValue(getAllNews.getAllNews())}
                getAllCategories()
            }
            else{
                _newsList.postValue(getNewsWithCategory.getNewsWithCategory(_chosenCategory.value ?: "All"))
            }
            _isRefreshing.value = false
        }

    }


    fun getAllNews(){
        Log.d("data", "LOADING")
        viewModelScope.launch {
            val news = withContext(Dispatchers.IO) {
                getAllNews.getAllNews()
            }
            _newsList.value = news
            _errorState.postValue(news.isEmpty())

            getAllCategories()

        }
    }

    fun getNewsWithCategory(category: String) {
        Log.d("category", "LOADING")

        viewModelScope.launch {
            val currentCategory = _chosenCategory.value

            if (currentCategory == category) {

                _newsList.postValue(getAllNews.getAllNews())
                _chosenCategory.postValue("All")
            } else {

                _newsList.postValue(getNewsWithCategory.getNewsWithCategory(category))
                _chosenCategory.postValue(category)
            }
        }
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