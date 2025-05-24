package com.example.newsaggregator.domain.usecases

import com.example.newsaggregator.domain.entity.NewsItem
import com.example.newsaggregator.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsWithCategory @Inject constructor(private val repository: NewsRepository)  {

    suspend fun getNewsWithCategory(category : String) : List<NewsItem>{
        return repository.getNewsWithCategory(category)
    }

}