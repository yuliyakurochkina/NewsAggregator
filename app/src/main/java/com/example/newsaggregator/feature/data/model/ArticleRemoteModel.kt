package com.example.newsaggregator.feature.data.model

import androidx.annotation.NonNull
import com.google.gson.annotations.SerializedName

data class ArticleRemoteModel(
    @NonNull
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String?,
    @SerializedName("publishedAt")
    val publishedAt: String
)