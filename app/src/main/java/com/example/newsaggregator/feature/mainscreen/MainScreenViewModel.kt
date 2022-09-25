package com.example.newsaggregator.feature.mainscreen

import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.feature.bookmarks.domain.BookmarksInteractor
import com.example.newsaggregator.feature.domain.ArticlesInteractor
import com.example.newsaggregator.base.BaseViewModel
import com.example.newsaggregator.base.Event
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val interactor: ArticlesInteractor,
    private val bookmarksInteractor: BookmarksInteractor
) : BaseViewModel<ViewState>() {

    init {
        processDataEvent(DataEvent.LoadArticles)
    }

    override fun initialViewState() = ViewState(
        articlesList = emptyList(),
        articlesShown = emptyList(),
        hasErrorHappened = false,
        isSearchEnabled = false,
        isLoading = false
    )

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is DataEvent.LoadArticles -> {
                viewModelScope.launch {
                    interactor.getArticles().fold(
                        onError = {
                            processDataEvent(DataEvent.OnLoadArticlesFailed)
                        },
                        onSuccess = {
                            processDataEvent(DataEvent.OnLoadArticlesSucceed(it))
                        }
                    )
                }
                return null
            }

            // Status of articles
            is DataEvent.OnLoadArticlesSucceed -> {
                return previousState.copy(
                    articlesList = event.articles,
                    articlesShown = event.articles
                )
            }

            is DataEvent.OnLoadArticlesFailed -> {
                return previousState.copy(
                    articlesShown = emptyList(),
                    hasErrorHappened = true
                )
            }

            // Search the article
            is UiEvent.OnSearchButtonClicked -> {
                return previousState.copy(
                    articlesShown = if (previousState.isSearchEnabled) previousState.articlesList else previousState.articlesShown,
                    isSearchEnabled = !previousState.isSearchEnabled
                )
            }

            is UiEvent.OnSearchEdit -> {
                return previousState.copy(articlesShown = previousState.articlesList.filter {
                    it.title.contains(
                        event.text
                    )
                })
            }

            // Click on article
            is UiEvent.OnArticleClicked -> {
                previousState.articlesList[event.index].bookmarkFav = true
                viewModelScope.launch {
                    bookmarksInteractor.create(previousState.articlesShown[event.index])
                }
                return null
            }
            else -> return null
        }
    }
}
