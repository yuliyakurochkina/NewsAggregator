package com.example.newsaggregator.feature.data

import com.example.newsaggregator.feature.domain.ArticleModel

class ArticleRepositoryImpl(val source: ArticlesRemoteSource) : ArticlesRepository {

    override suspend fun getArticles(): List<ArticleModel> {
        return source.getArticles().articleList.map {
            it.toDomain()
        }
    }
}