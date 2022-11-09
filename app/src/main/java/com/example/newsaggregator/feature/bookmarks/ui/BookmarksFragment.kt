package com.example.newsaggregator.feature.bookmarks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.R
import com.example.newsaggregator.base.openArticleOnClick
import com.example.newsaggregator.databinding.FragmentBookmarksBinding
import com.example.newsaggregator.databinding.FragmentMainScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarksFragment : Fragment() {

    private val adapter: BookmarksArticlesAdapter by lazy {
        BookmarksArticlesAdapter({ index ->
            viewModel.processUiEvent(UiEvent.OnDeleteFromBookmarksClicked(index))
        }, { currentArticle -> openArticleOnClick(currentArticle) }, this@BookmarksFragment)
    }

    private lateinit var binding: FragmentBookmarksBinding

    private val rvArticles: RecyclerView by lazy { requireActivity().findViewById(R.id.rvBookmarkedArticles) }
    private val viewModel: BookmarksScreenViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarksBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        rvArticles.adapter = adapter
    }

    private fun render(viewState: ViewState) {
        binding.ivNoBookmarksNotification.isVisible = viewState.isBookmarksEmpty
        binding.tvNoBookmarksNotification.isVisible = viewState.isBookmarksEmpty

        adapter.setData(viewState.bookmarksArticle)
    }
}

