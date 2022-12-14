package com.example.newsaggregator.feature.description_screen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.newsaggregator.R
import com.example.newsaggregator.databinding.FragmentDescriptionScreenBinding
import com.example.newsaggregator.di.KEY_FOR_ARTICLE_MODEL
import com.example.newsaggregator.feature.domain.ArticleModel
import com.google.android.material.appbar.AppBarLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class DescriptionScreenFragment : Fragment(R.layout.fragment_description_screen) {

    private lateinit var binding: FragmentDescriptionScreenBinding
    private val viewModel: DescriptionScreenViewModel by viewModel()

    companion object {
        fun newInstance(args: Bundle?): DescriptionScreenFragment {
            val descriptionScreenFragment = DescriptionScreenFragment()
            descriptionScreenFragment.arguments = args
            return descriptionScreenFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptionScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newsAppBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val percent =
                (kotlin.math.abs(appBarLayout.totalScrollRange + verticalOffset)
                    .toFloat() / appBarLayout.totalScrollRange)
        })

        binding.backToMainScreen.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this)
                .commit()
        }

        // getArguments() - возвращение аргументов, предоставленных при создании экземпляра фрагмента
        val currNews: ArticleModel = this.arguments?.get(KEY_FOR_ARTICLE_MODEL) as ArticleModel
        val title = arguments?.get("title").toString()
        val url = arguments?.get("url").toString()

        binding.collapsingToolbar.title = title
        binding.linkOfArticle.text = url
        viewModel.processUiEvent(DataEvent.ShowNews(currNews))
        viewModel.viewState.observe(viewLifecycleOwner, ::render)

        binding.linkOfArticle.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

    }

    private fun render(viewState: ViewState) {
        binding.linkOfArticle.text = viewState.url
        binding.tvDescriptionD.text = viewState.description
        binding.tvContent.text = viewState.content
        binding.tvPublishedAt.text = viewState.publishedAt
        viewState.urlToImage?.let { getImageFromUrl(it) }
    }

    private fun getImageFromUrl(urlToImage: String) {
        Glide.with(this)
            .load(urlToImage)
            .into(binding.newsPreview)
    }
}