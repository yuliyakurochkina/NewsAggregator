package com.example.newsaggregator.feature.di

import com.example.newsaggregator.feature.data.ArticleRepositoryImpl
import com.example.newsaggregator.feature.data.ArticlesRemoteSource
import com.example.newsaggregator.feature.data.ArticlesRepository
import com.example.newsaggregator.feature.data.NewsApi
import com.example.newsaggregator.feature.domain.ArticlesInteractor
import com.example.newsaggregator.feature.mainscreen.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val mainScreenModule = module {

    single<NewsApi> {
        get<Retrofit>().create(NewsApi::class.java)
    }

    single<ArticlesRemoteSource> {
        ArticlesRemoteSource(api = get())
    }

    single<ArticlesRepository> {
        ArticleRepositoryImpl(source = get())
    }

    single<ArticlesInteractor> {
        ArticlesInteractor(repository = get())
    }

    viewModel {
        MainScreenViewModel(interactor = get(), bookmarksInteractor = get())
    }
}