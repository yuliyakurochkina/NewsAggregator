package com.example.newsaggregator.feature.data.model

import com.google.gson.annotations.SerializedName

data class ArticlesRemoteModel(
    @SerializedName("articles")
    val articleList: List<ArticleRemoteModel>
)