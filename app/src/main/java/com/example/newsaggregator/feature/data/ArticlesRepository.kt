package com.example.newsaggregator.feature.data

import com.example.newsaggregator.feature.domain.ArticleModel

interface ArticlesRepository {
    suspend fun getArticlesFromRepository(): List<ArticleModel>
}