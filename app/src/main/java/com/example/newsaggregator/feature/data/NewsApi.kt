package com.example.newsaggregator.feature.data

import com.example.newsaggregator.feature.data.model.ArticlesRemoteModel
import com.example.newsaggregator.di.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/top-headlines")
    suspend fun getArticlesFromNewsApi(
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("country") country: String = "us"
    ): ArticlesRemoteModel
}