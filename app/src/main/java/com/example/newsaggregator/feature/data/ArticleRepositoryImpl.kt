package com.example.newsaggregator.feature.data

import com.example.newsaggregator.feature.domain.ArticleModel

class ArticleRepositoryImpl(val source: ArticlesRemoteSource) : ArticlesRepository {

    override suspend fun getArticlesFromRepository(): List<ArticleModel> {
        return source.getArticlesFromRemoteSource().articleList.map {
            it.toDomain()
        }
    }
}