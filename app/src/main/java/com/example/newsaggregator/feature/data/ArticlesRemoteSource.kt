package com.example.newsaggregator.feature.data

import com.example.newsaggregator.feature.data.model.ArticlesRemoteModel

class ArticlesRemoteSource(private val api: NewsApi) {
    suspend fun getArticlesFromRemoteSource(): ArticlesRemoteModel {
        return api.getArticlesFromNewsApi()
    }
}