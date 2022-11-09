package com.example.newsaggregator.feature.mainscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsaggregator.feature.domain.ArticleModel
import com.example.newsaggregator.R

class ArticlesAdapter(
    private val onAddToBookmarksClicked: (Int) -> Unit,
    private val onArticleClicked: (ArticleModel) -> Unit,
    private val context: MainScreenFragment
) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    private var articlesData: List<ArticleModel> = emptyList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvDate: TextView = view.findViewById(R.id.tvDate)
        val ivArticleImage: ImageView = view.findViewById(R.id.ivFromArticle)
        val ivBookmarkAdded: ImageView = view.findViewById(R.id.ivBookmarkAdded)
        val ivAddToBookmarks: ImageView =
            view.findViewById<ImageView?>(R.id.ivAddToBookmarks)
                .also { it.visibility = ImageView.VISIBLE }
    }

    // Создание новых представлений (вызывается менеджером layout)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Создать новое представление, определяющее пользовательский интерфейс элемента списка
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_article, viewGroup, false)
        return ViewHolder(view)
    }

    // Заменить содержимое представления (вызывается менеджером layout)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.ivAddToBookmarks.setOnClickListener {
            onAddToBookmarksClicked.invoke(position)
            notifyDataSetChanged()
        }

        viewHolder.itemView.setOnClickListener {
            onArticleClicked.invoke(articlesData[position])
        }

        viewHolder.ivBookmarkAdded.visibility =
            if (articlesData[position].bookmarkFav) ImageView.VISIBLE else ImageView.GONE

        if (!articlesData[position].urlToImage.isNullOrEmpty()) {
            Glide.with(context)
                .load(articlesData[position].urlToImage)
                .centerCrop()
                .into(viewHolder.ivArticleImage)
        }

        // Получить элемент из набора данных в этой позиции и заменить
        // содержимое представления с этим элементом
        viewHolder.tvDate.text = articlesData[position].publishedAt
        viewHolder.tvTitle.text = articlesData[position].title
    }

    // Вернуть размер набора данных (вызывается менеджером layout)
    override fun getItemCount() = articlesData.size

    fun setData(articles: List<ArticleModel>) {
        articlesData = articles
        notifyDataSetChanged()
    }
}