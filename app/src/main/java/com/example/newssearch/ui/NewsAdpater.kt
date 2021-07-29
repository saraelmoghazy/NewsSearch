package com.example.newssearch.ui

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newssearch.databinding.PartialNewsBinding
import com.example.newssearch.model.ArticlesItem

class NewsAdapter(val articleClickListener: ArticleClickListener?) :
    PagingDataAdapter<ArticlesItem, NewsAdapter.NewsViewHolder>(NewsComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsViewHolder(
            PartialNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class NewsViewHolder(private val binding: PartialNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                articleClickListener?.onArticleClicked(
                    getItem(absoluteAdapterPosition) as ArticlesItem
                )
            }
        }

        fun bind(item: ArticlesItem) = with(binding) {
            article = item
            binding.txtSource.text = Html.fromHtml(item.url, Html.FROM_HTML_MODE_COMPACT)
        }
    }

    object NewsComparator : DiffUtil.ItemCallback<ArticlesItem>() {
        override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem) =
            oldItem == newItem
    }

    interface ArticleClickListener {
        fun onArticleClicked(article: ArticlesItem)
    }
}