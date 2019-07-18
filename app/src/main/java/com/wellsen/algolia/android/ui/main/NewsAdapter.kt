package com.wellsen.algolia.android.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wellsen.algolia.android.R
import com.wellsen.algolia.android.data.remote.response.News
import com.wellsen.algolia.android.databinding.RowNewsBinding
import com.wellsen.algolia.android.ui.BindingViewHolder

/**
 * Project algolia.
 *
 * Created by wellsen on 2019-07-18.
 */
class NewsAdapter(var news: List<News> = arrayListOf(), val vm: MainViewModel) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.row_news,
            parent,
            false
        )

        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.binding.news = news[position]
        holder.binding.vm = vm

        holder.binding.ll.setOnClickListener {
            listener.onClick(it, news = news[position])
        }
    }

    override fun getItemCount() = news.size

    class NewsViewHolder(view: View) : BindingViewHolder<RowNewsBinding>(view)

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onClick(view: View, news: News)
    }
}
