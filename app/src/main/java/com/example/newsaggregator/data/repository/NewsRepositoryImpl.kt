package com.example.newsaggregator.data.repository

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.newsaggregator.data.infrastructure.DataBaseDriver
import com.example.newsaggregator.data.rss.NewsDataSource
import com.example.newsaggregator.domain.entity.NewsItem
import com.example.newsaggregator.domain.repository.NewsRepository
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class NewsRepositoryImpl @Inject constructor(
    private val context: Context,
    private val dataSource: NewsDataSource,
    private val dataBase: DataBaseDriver
) : NewsRepository {

    private fun cleanHtmlTags(text: String): String = text.replace(Regex("<.*?>"), "")

    private fun parseDateOrDefault(dateString: String?): ZonedDateTime {
        return try {
            ZonedDateTime.parse(dateString, DateTimeFormatter.RFC_1123_DATE_TIME)
        } catch (e: Exception) {
            ZonedDateTime.now().minusYears(100)
        }
    }

    private fun mapToNewsItem(
        title: String?,
        desc: String?,
        icon: String?,
        date: String?,
        author: String?,
        url: String,
        category: String?
    ) = NewsItem(
        name = title ?: "Check the internet connection",
        desc = desc ?: "Check the internet connection",
        icon = icon ?: "",
        date = date ?: "",
        author = author ?: "",
        url = url,
        category = category ?: ""
    )

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getAllNews(): List<NewsItem> {
        return try {
            val fetchedNews = dataSource.getNews()
            val newsList = fetchedNews.channel.items.map {
                val iconUrl = it.contents.lastOrNull()?.url ?: ""
                dataBase.database.newsQueries.insertIntoDB(
                    url = iconUrl,
                    title = it.title,
                    desc = cleanHtmlTags(it.description),
                    icon = iconUrl,
                    date = it.pubDate,
                    author = it.dcCreator,
                    category = it.categories.lastOrNull()?.value ?: ""
                )
                mapToNewsItem(
                    title = it.title,
                    desc = cleanHtmlTags(it.description),
                    icon = iconUrl,
                    date = it.pubDate,
                    author = it.dcCreator,
                    url = it.guid,
                    category = it.categories.lastOrNull()?.value ?: ""
                )
            }

            newsList.sortedByDescending { parseDateOrDefault(it.date) }

        } catch (e: Exception) {
            Log.e("retrofit", "Error fetching news: $e")
            val cachedNews = dataBase.database.newsQueries.getFromDBAll().executeAsList()
            cachedNews.map {
                mapToNewsItem(
                    title = it.title,
                    desc = it.desc,
                    icon = it.icon,
                    date = it.date,
                    author = it.author,
                    url = it.url,
                    category = it.category
                )
            }.sortedByDescending { parseDateOrDefault(it.date) }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getNewsWithCategory(category: String): List<NewsItem> {
        val cachedNews = dataBase.database.newsQueries.getFromDBCategory(category).executeAsList()
        return cachedNews.map {
            mapToNewsItem(
                title = it.title,
                desc = it.desc,
                icon = it.icon,
                date = it.date,
                author = it.author,
                url = it.url,
                category = it.category
            )
        }.sortedByDescending { parseDateOrDefault(it.date) }
    }

    override  fun prepareShareContent(url: String): String {
        return "$url"
    }
}


