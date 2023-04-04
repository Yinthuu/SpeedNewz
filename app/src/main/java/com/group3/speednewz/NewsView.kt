package com.group3.speednewz

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.group3.speednewz.models.NewsData

class NewsView(
    containerView: View,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(containerView) {
    private val imageView: ImageView by lazy {
        containerView.findViewById(R.id.news_image)
    }
    private val titleView: TextView by lazy {
        containerView.findViewById(R.id.news_title)
    }

    fun bindData(newsData: NewsData) {
        imageLoader.loadImage(newsData.imageURL, imageView)
        titleView.text = newsData.title
    }
}
