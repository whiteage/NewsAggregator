package com.example.newsaggregator.domain.entity

data class NewsItem(
    val name : String,
    val desc : String,
    val icon : String,
    val date : String,
    val author : String,
    val url : String,
    val category: String

)
