package com.example.newsaggregator.feature.mainscreen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.R
import com.example.newsaggregator.base.openArticleOnClick
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment() {

    private val adapter: ArticlesAdapter by lazy {
        ArticlesAdapter({ index ->
            viewModel.processUiEvent(UiEvent.OnArticleClicked(index))
        }, { currentArticle -> openArticleOnClick(currentArticle) }, this@MainScreenFragment)
    }

    private val etSearch: EditText by lazy { requireActivity().findViewById(R.id.etSearch) }
    private val ivSearch: ImageView by lazy { requireActivity().findViewById(R.id.ivSearch) }
    private val progressBar: ProgressBar by lazy { requireActivity().findViewById(R.id.progressBar) }
    private val recyclerView: RecyclerView by lazy { requireActivity().findViewById(R.id.rvArticles) }
    private val tvSearchTitle: TextView by lazy { requireActivity().findViewById(R.id.tvSearchTitle) }
    private val viewModel: MainScreenViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(text: Editable?) {
                viewModel.processUiEvent(UiEvent.OnSearchEdit(text.toString()))
            }
        })

        ivSearch.setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnSearchButtonClicked)
        }

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        recyclerView.adapter = adapter
    }

    private fun render(viewState: ViewState) {
        adapter.setData(viewState.articlesShown)
        etSearch.isVisible = viewState.isSearchEnabled
        progressBar.isVisible = viewState.isLoading
        tvSearchTitle.isVisible = !viewState.isSearchEnabled
    }

}