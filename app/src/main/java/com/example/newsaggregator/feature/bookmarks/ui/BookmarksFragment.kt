package com.example.newsaggregator.feature.bookmarks.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.R
import com.example.newsaggregator.base.openArticleOnClick
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarksFragment : Fragment(R.layout.fragment_bookmarks) {

    private val viewModel: BookmarksScreenViewModel by viewModel()
    private val rvArticles: RecyclerView by lazy { requireActivity().findViewById(R.id.rvBookmarkedArticles) }
    private val adapter: BookmarksArticlesAdapter by lazy {
        BookmarksArticlesAdapter({ index ->
            viewModel.processUiEvent(UiEvent.OnDeleteFromBookmarksClicked(index))
        }, { currentArticle -> openArticleOnClick(currentArticle) }, this@BookmarksFragment)
    }
    private val icNoBookmarks: ImageView by lazy { requireActivity().findViewById(R.id.ivNoBookmarksNotification) }
    private val tvNoBookmarks: TextView by lazy { requireActivity().findViewById(R.id.tvNoBookmarksNotification) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        rvArticles.adapter = adapter
    }

    private fun render(viewState: ViewState) {
        adapter.setData(viewState.bookmarksArticle)
        icNoBookmarks.isVisible = viewState.isBookmarksEmpty
        tvNoBookmarks.isVisible = viewState.isBookmarksEmpty
    }
}

