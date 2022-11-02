package com.example.newsaggregator.feature.data

import com.example.newsaggregator.feature.data.model.ArticleRemoteModel
import com.example.newsaggregator.feature.domain.ArticleModel

fun ArticleRemoteModel.toDomain() = ArticleModel(
    title = title,
    description = description ?: "",
    content = content ?: "",
    url = url,
    urlToImage = urlToImage ?: "",
    publishedAt = publishedAt.replace("T", "  ").replace("Z",""),
)