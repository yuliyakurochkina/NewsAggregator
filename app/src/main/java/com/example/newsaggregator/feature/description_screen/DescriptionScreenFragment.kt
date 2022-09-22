package com.example.newsaggregator.feature.description_screen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.newsaggregator.R
import com.example.newsaggregator.di.KEY_FOR_ARTICLE_MODEL
import com.example.newsaggregator.feature.domain.ArticleModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class DescriptionScreenFragment : Fragment(R.layout.fragment_description_screen) {

    private val newsAppBar: AppBarLayout by lazy { requireActivity().findViewById(R.id.newsAppBar) }
    private val backToMainScreen: ImageView by lazy { requireActivity().findViewById(R.id.backToMainScreen) }
    private val collapsingToolbar: CollapsingToolbarLayout by lazy {
        requireActivity().findViewById(
            R.id.collapsingToolbar
        )
    }
    private val newsPreview: ImageView by lazy { requireActivity().findViewById(R.id.newsPreview) }
    private val linkOfArticle: TextView by lazy { requireActivity().findViewById(R.id.linkOfArticle) }
    private val tvDescriptionD: TextView by lazy { requireActivity().findViewById(R.id.tvDescriptionD) }
    private val tvContent: TextView by lazy { requireActivity().findViewById(R.id.tvContent) }
    private val tvPublishedAt: TextView by lazy { requireActivity().findViewById(R.id.tvPublishedAt) }
    private val viewModel: DescriptionScreenViewModel by viewModel()

    companion object {
        fun newInstance(args: Bundle?): DescriptionScreenFragment {
            val descriptionScreenFragment = DescriptionScreenFragment()
            descriptionScreenFragment.arguments = args
            return descriptionScreenFragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsAppBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val percent =
                (kotlin.math.abs(appBarLayout.totalScrollRange + verticalOffset)
                    .toFloat() / appBarLayout.totalScrollRange)
        })

        backToMainScreen.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this)
                .commit()
        }

        // getArguments() - возвращение аргументов, предоставленных при создании экземпляра фрагмента
        val currNews: ArticleModel = this.arguments?.get(KEY_FOR_ARTICLE_MODEL) as ArticleModel
        val title = arguments?.get("title").toString()
        val url = arguments?.get("url").toString()

        collapsingToolbar.title = title
        linkOfArticle.text = url
        viewModel.processUiEvent(DataEvent.ShowNews(currNews))
        viewModel.viewState.observe(viewLifecycleOwner, ::render)

        linkOfArticle.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

    }

    private fun render(viewState: ViewState) {
        linkOfArticle.text = viewState.url
        tvDescriptionD.text = viewState.description
        tvContent.text = viewState.content
        tvPublishedAt.text = viewState.publishedAt
        viewState.urlToImage?.let { getImageFromUrl(it) }
    }

    private fun getImageFromUrl(urlToImage: String) {
        Glide.with(this)
            .load(urlToImage)
            .into(newsPreview)
    }
}