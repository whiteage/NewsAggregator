package com.example.newsaggregator.domain.repository

import com.example.newsaggregator.domain.entity.NewsItem

interface NewsRepository {

    suspend fun getAllNews() : List<NewsItem>
    suspend fun getNewsWithCategory(category: String) : List<NewsItem>


}