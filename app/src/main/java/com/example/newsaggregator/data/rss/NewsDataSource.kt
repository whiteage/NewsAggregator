package com.example.newsaggregator.data.rss

import com.example.newsaggregator.data.rss.dto.RssDto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import nl.adaptivity.xmlutil.serialization.XML
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


class NewsDataSource {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.theguardian.com")
        .addConverterFactory(
            XML.asConverterFactory(
                "application/xml; charset=UTF8".toMediaType()
            )
        ).build()

    private val guardian = retrofit.create(RssFeed::class.java)

    suspend fun getNews() : RssDto{
        return guardian.getRss()
    }


}