package com.example.excercise1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load

import com.example.excercise1.databinding.ItemNewsBinding
import com.example.excercise1.paging.ArticleWithSentiment

class NewsAdapter :
    PagingDataAdapter<ArticleWithSentiment, NewsAdapter.NewsViewHolder>(DIFF) {

    inner class NewsViewHolder(val binding: ItemNewsBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = getItem(position) ?: return

        holder.binding.title.text = item.article.title
        holder.binding.content.text = item.article.content ?: ""

        // Load image (safe)
        holder.binding.image.load(item.article.urlToImage) {
            crossfade(true)
            placeholder(android.R.drawable.ic_menu_report_image)
            error(android.R.drawable.ic_menu_report_image)
        }

        val sentiment = item.sentiment

        val color = if (sentiment.positive > sentiment.negative) {
            0xFFCCFFCC.toInt()
        } else {
            0xFFFFCCCC.toInt()
        }

        // 👉 IMPORTANT: dùng CardView
        holder.binding.root.setCardBackgroundColor(color)
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<ArticleWithSentiment>() {
            override fun areItemsTheSame(
                oldItem: ArticleWithSentiment,
                newItem: ArticleWithSentiment
            ) = oldItem.article.title == newItem.article.title

            override fun areContentsTheSame(
                oldItem: ArticleWithSentiment,
                newItem: ArticleWithSentiment
            ) = oldItem == newItem
        }
    }
}