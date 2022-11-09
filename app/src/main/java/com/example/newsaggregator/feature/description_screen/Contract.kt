package com.example.newsaggregator.feature.description_screen

import com.example.newsaggregator.base.Event
import com.example.newsaggregator.feature.domain.ArticleModel

data class ViewState(
    val title: String,
    val description: String?,
    val content: String?,
    val publishedAt:String?,
    val urlToImage: String?,
    val url: String?
)

sealed class UiEvent : Event {
}

sealed class DataEvent() : Event {
    data class ShowNews(val currNews: ArticleModel): DataEvent()
}