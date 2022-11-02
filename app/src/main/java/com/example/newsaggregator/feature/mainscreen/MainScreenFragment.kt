package com.example.newsaggregator.feature.mainscreen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.R
import com.example.newsaggregator.base.openArticleOnClick
import com.example.newsaggregator.databinding.FragmentMainScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment() {

    private val adapter: ArticlesAdapter by lazy {
        ArticlesAdapter({ index ->
            viewModel.processUiEvent(UiEvent.OnArticleClicked(index))
        }, { currentArticle -> openArticleOnClick(currentArticle) }, this@MainScreenFragment)
    }

    private lateinit var binding: FragmentMainScreenBinding

    private val recyclerView: RecyclerView by lazy { requireActivity().findViewById(R.id.rvArticles) }
    private val viewModel: MainScreenViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(text: Editable?) {
                viewModel.processUiEvent(UiEvent.OnSearchEdit(text.toString()))
            }
        })

         binding.ivSearch.setOnClickListener {
             viewModel.processUiEvent(UiEvent.OnSearchButtonClicked)
         }

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        recyclerView.adapter = adapter
    }

    private fun render(viewState: ViewState) {
        binding.etSearch.isVisible = viewState.isSearchEnabled
        binding.progressBar.isVisible = viewState.isLoading
        binding.tvSearchTitle.isVisible = !viewState.isSearchEnabled

        adapter.setData(viewState.articlesShown)
    }

}