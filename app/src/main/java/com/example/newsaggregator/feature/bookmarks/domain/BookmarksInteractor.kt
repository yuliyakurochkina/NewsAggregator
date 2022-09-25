package com.example.newsaggregator.feature.bookmarks.domain

import com.example.newsaggregator.feature.bookmarks.data.local.BookmarksRepository
import com.example.newsaggregator.feature.domain.ArticleModel
import com.example.newsaggregator.base.Either
import com.example.newsaggregator.base.attempt

class BookmarksInteractor(private val bookmarksRepository: BookmarksRepository) {

    suspend fun create(model: ArticleModel) {
        attempt { bookmarksRepository.create(model) }
    }

    suspend fun read(): Either<Throwable, List<ArticleModel>> {
        return attempt { bookmarksRepository.read() }
    }

    suspend fun update(model: ArticleModel) {
        attempt { bookmarksRepository.update(model) }
    }

    suspend fun delete(model: ArticleModel) {
        attempt { bookmarksRepository.delete(model) }
    }
}