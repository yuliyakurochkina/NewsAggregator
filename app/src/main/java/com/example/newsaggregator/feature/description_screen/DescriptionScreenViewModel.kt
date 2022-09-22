package com.example.newsaggregator.feature.description_screen

import com.example.newsaggregator.base.BaseViewModel
import com.example.newsaggregator.base.Event

class DescriptionScreenViewModel() : BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState = ViewState(
        title = "",
        description = "",
        content = "",
        publishedAt = "",
        urlToImage = "",
        url = ""
    )

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is DataEvent.ShowNews -> {
                return previousState.copy(
                    title = event.currNews.title,
                    description = event.currNews.description,
                    content = event.currNews.content?.replace(Regex("\\[.*"),"Читать полностью:"),
                    publishedAt = event.currNews.publishedAt,
                    urlToImage = event.currNews.urlToImage,
                    url = event.currNews.url
                )
            }
        }
        return null
    }
}
