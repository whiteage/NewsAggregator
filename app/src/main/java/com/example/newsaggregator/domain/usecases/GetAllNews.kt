package com.example.newsaggregator.domain.usecases

import com.example.newsaggregator.domain.entity.NewsItem
import com.example.newsaggregator.domain.repository.NewsRepository
import javax.inject.Inject

class GetAllNews @Inject constructor(private val repository: NewsRepository) {

    suspend fun getAllNews() : List<NewsItem>{
        return repository.getAllNews()
    }

}
