package com.example.newsaggregator.feature.bookmarks.ui

import com.example.newsaggregator.feature.domain.ArticleModel
import com.example.newsaggregator.base.Event

data class ViewState(
    val bookmarksArticle: List<ArticleModel>,
    var isBookmarksEmpty: Boolean
)

sealed class UiEvent() : Event {
    data class OnDeleteFromBookmarksClicked(val index: Int) : UiEvent()
}

sealed class DataEvent() : Event {
    object LoadBookmarks : DataEvent()
    data class OnSuccessBookmarksLoaded(val bookmarksArticle: List<ArticleModel>) : DataEvent()
    data class OnFailedBookmarksLoaded(val throwable: Throwable) : DataEvent()
}