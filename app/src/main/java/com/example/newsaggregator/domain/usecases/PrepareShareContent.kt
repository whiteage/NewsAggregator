package com.example.newsaggregator.domain.usecases

import com.example.newsaggregator.domain.repository.NewsRepository
import javax.inject.Inject


class PrepareShareContent @Inject constructor(private val repository: NewsRepository){

    fun prepareShareContent(url: String) : String{
        return repository.prepareShareContent(url)
    }

}