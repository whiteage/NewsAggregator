package com.example.newsaggregator.data.infrastructure

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.newsaggregator.Database


class DataBaseDriver(context : Context) {

    private val driver = AndroidSqliteDriver(
        schema = Database.Schema,
        context = context,
        name = "news.db"
    )
    val database = Database(driver)


}