package com.example.newsappassignment.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsappassignment.R
import com.example.newsappassignment.databinding.NewsArticleAdapterLayoutBinding
import com.example.newsappassignment.storage.entity.NewsArticleDb

class NewsArticlesAdapter :
    ListAdapter<NewsArticleDb, NewsArticlesAdapter.NewsHolder>(DiffCallBack()) {
    private lateinit var viewBinding: NewsArticleAdapterLayoutBinding

    inner class NewsHolder(var viewBinding: NewsArticleAdapterLayoutBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(holderData: NewsArticleDb) {
            viewBinding.modelClass = holderData
        }
    }

    fun addDataToAdapter(articles: List<NewsArticleDb>) {
        submitList(articles)
    }

    class DiffCallBack : DiffUtil.ItemCallback<NewsArticleDb>() {
        override fun areItemsTheSame(oldItem: NewsArticleDb, newItem: NewsArticleDb): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: NewsArticleDb,
            newItem: NewsArticleDb
        ): Boolean = oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        viewBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.news_article_adapter_layout,
            parent,
            false
        )
        return NewsHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val holderData = getItem(holder.absoluteAdapterPosition)
        holder.bind(holderData)
    }
}