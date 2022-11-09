package com.example.newsaggregator.feature.domain

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull

@Parcelize
data class ArticleModel(
    @NonNull
    val title: String,
    val description: String?,
    val content: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    var bookmarkFav: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<ArticleModel> {
        override fun createFromParcel(parcel: Parcel): ArticleModel {
            return ArticleModel(parcel)
        }

        override fun newArray(size: Int): Array<ArticleModel?> {
            return arrayOfNulls(size)
        }
    }
}

annotation class Parcelize
