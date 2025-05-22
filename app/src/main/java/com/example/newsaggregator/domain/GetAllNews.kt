package com.example.newsaggregator.domain

class GetAllNews(private val repository: NewsRepository) {

    suspend fun getAllNews() : List<NewsItem>{
        return repository.getAllNews()
    }
}