package com.example.newsaggregator.feature.mainscreen

import com.example.newsaggregator.feature.domain.ArticleModel
import com.example.newsaggregator.base.Event

data class ViewState(
    val articlesList: List<ArticleModel>,
    val articlesShown: List<ArticleModel>,
    val hasErrorHappened: Boolean,
    val isSearchEnabled: Boolean,
    val isLoading: Boolean
)

sealed class UiEvent : Event {
    data class OnArticleClicked(val index: Int): UiEvent()
    object OnSearchButtonClicked : UiEvent()
    data class OnSearchEdit(val text: String) : UiEvent()
}

sealed class DataEvent : Event {
    object LoadArticles: DataEvent()
    data class OnLoadArticlesSucceed(val articles: List<ArticleModel>): DataEvent()
    object OnLoadArticlesFailed : DataEvent()
}