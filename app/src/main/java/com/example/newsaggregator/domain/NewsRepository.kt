package com.example.newsaggregator.domain

interface NewsRepository {

    suspend fun getAllNews() : List<NewsItem>


}