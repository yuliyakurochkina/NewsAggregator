package com.example.newsaggregator.feature.data

import com.example.newsaggregator.feature.domain.ArticleModel

interface ArticlesRepository {
    suspend fun getArticles(): List<ArticleModel>
}