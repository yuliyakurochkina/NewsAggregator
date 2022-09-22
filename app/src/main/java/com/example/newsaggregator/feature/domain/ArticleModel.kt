package com.example.newsaggregator.feature.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleModel(
    val title: String,
    val description: String,
    val content: String?,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    var bookmarkFav: Boolean = false
) : Parcelable
