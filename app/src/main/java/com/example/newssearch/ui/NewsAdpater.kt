package com.example.newssearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newssearch.databinding.PartialNewsBinding
import com.example.newssearch.model.ArticlesItem

class NewsAdapter :
    PagingDataAdapter<ArticlesItem, NewsAdapter.NewsViewHolder>(NewsComparator) {
    var articleClickListener: ArticleClickListener? = null
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
                    binding,
                    getItem(absoluteAdapterPosition) as ArticlesItem
                )
            }
        }

        fun bind(item: ArticlesItem) = with(binding) {
            article = item
        }
    }

    object NewsComparator : DiffUtil.ItemCallback<ArticlesItem>() {
        override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem) =
            oldItem == newItem
    }

    interface ArticleClickListener {
        fun onArticleClicked(binding: PartialNewsBinding, article: ArticlesItem)
    }
}