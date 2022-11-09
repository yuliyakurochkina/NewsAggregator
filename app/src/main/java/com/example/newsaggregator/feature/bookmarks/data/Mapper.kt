package com.example.newsaggregator.feature.bookmarks.data

import com.example.newsaggregator.feature.bookmarks.data.local.model.BookmarkEntity
import com.example.newsaggregator.feature.domain.ArticleModel

fun BookmarkEntity.toDomain() = ArticleModel(
    title = title ?: "",
    description = description ?: "",
    content = content ?: "",
    url = url ?: "",
    urlToImage = urlToImage ?: "",
    publishedAt = publishedAt ?: ""
)

fun ArticleModel.toEntity() = BookmarkEntity(
    title = title,
    description = description,
    content = content,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt
)