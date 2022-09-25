package com.example.newsaggregator.feature.data

import com.example.newsagg.feature.data.model.ArticlesRemoteModel

class ArticlesRemoteSource(private val api: NewsApi) {
    suspend fun getArticles(): ArticlesRemoteModel {
        return api.getArticles()
    }
}