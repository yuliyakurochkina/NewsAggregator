package com.example.newsaggregator.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.newsaggregator.feature.description_screen.DescriptionScreenFragment
import com.example.newsaggregator.feature.domain.ArticleModel
import com.example.newsaggregator.R
import com.example.newsaggregator.di.KEY_FOR_ARTICLE_MODEL

inline fun <reified T> attempt(func: () -> T): Either<Throwable, T> = try {
    Either.Right(func.invoke())
} catch (e: Throwable) {
    Either.Left(e)
}

fun Fragment.openArticleOnClick(currentArticle: ArticleModel) {
    val bundle = Bundle()
    bundle.putParcelable(KEY_FOR_ARTICLE_MODEL, currentArticle)
    bundle.putString("title", currentArticle.title)
    bundle.putString("url", currentArticle.url)
    parentFragmentManager.beginTransaction()
        .add(R.id.container, DescriptionScreenFragment.newInstance(bundle)).commit()
}