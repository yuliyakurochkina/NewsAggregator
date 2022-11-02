package com.example.newsaggregator.feature.domain

import com.example.newsaggregator.feature.data.ArticlesRepository
import com.example.newsaggregator.base.attempt

class ArticlesInteractor(private val repository: ArticlesRepository) {
    suspend fun getArticlesForMainScreen() = attempt { repository.getArticlesFromRepository() }
}