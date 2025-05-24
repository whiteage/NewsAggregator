package com.example.newsaggregator.di

import android.content.Context
import com.example.newsaggregator.data.infrastructure.DataBaseDriver
import com.example.newsaggregator.data.repository.NewsRepositoryImpl
import com.example.newsaggregator.data.rss.NewsDataSource
import com.example.newsaggregator.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideNewsDataSource(): NewsDataSource = NewsDataSource()

    @Provides
    @Singleton
    fun provideDatabaseDriver(@ApplicationContext context: Context): DataBaseDriver {
        return DataBaseDriver(context)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        dataSource: NewsDataSource,
        database: DataBaseDriver,
        @ApplicationContext context: Context
    ): NewsRepository {
        return NewsRepositoryImpl(context, dataSource, database)
    }
}