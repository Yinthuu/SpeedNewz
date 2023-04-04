package com.group3.speednewz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group3.speednewz.models.NewsData

class NewsAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<NewsView>() {
    private val newsList = mutableListOf<NewsData>()

    fun setData(catsData: List<NewsData>) {
        this.newsList.clear()
        this.newsList.addAll(catsData)
        notifyDataSetChanged()
    }

    fun setFavoritesData(newsList: List<NewsData>) {
        val favoritesList = newsList.filter { it.favorites }
        this.newsList.addAll(favoritesList)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsView {
        val view = layoutInflater.inflate(R.layout.news_view, parent, false)
        return NewsView(view, imageLoader)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsView, position: Int) {
        holder.bindData(newsList[position])
    }

}