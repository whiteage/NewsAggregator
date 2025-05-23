package com.example.newsaggregator.data.rss

import android.util.Log
import com.example.newsaggregator.domain.NewsItem
import com.example.newsaggregator.domain.NewsRepository
import retrofit2.http.Url

class NewsRepositoryImpl() : NewsRepository {

    val dataSource = NewsDataSource()

    override suspend fun getAllNews(): List<NewsItem> {
        val newList = mutableListOf<NewsItem>()
        try {
            val newsList = dataSource.getNews()
            newsList.channel.items.forEach {
                newList.add(NewsItem(
                    name = it.title,
                    desc = it.description.replace(Regex("<.*?>"),""),
                    icon = it.contents.lastOrNull()?.url ?: "",
                    date = it.pubDate,
                    author = it.dcCreator,
                    url = it.guid
                ))
            }
            return newList

        }
        catch (e : Exception){
            Log.d("retrofit", "THAT WAS AN ERROR: $e")
            return emptyList()
        }
    }



}
