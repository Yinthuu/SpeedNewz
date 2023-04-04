package com.group3.speednewz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.group3.speednewz.models.NewsData

class NewsDetails : AppCompatActivity() {
    private val titleView: TextView by lazy {
        findViewById(R.id.news_title)
    }
    private val imageView: ImageView by lazy {
        findViewById(R.id.news_image)
    }
    private val contentView: TextView by lazy {
        findViewById(R.id.news_content)
    }
    private val imageLoader = GlideImageLoader(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        val newsData = intent.getSerializableExtra("newsData") as? NewsData
        if (newsData != null) {
            titleView.text = newsData.title
            imageLoader.loadImage(newsData.imageURL, imageView)
            contentView.text = newsData.content
        }
    }
}