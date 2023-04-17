package com.group3.speednewz

import androidx.lifecycle.ViewModel
import com.group3.speednewz.models.NewsData

class NewsViewModel : ViewModel() {
    val newsItems: MutableList<NewsData> = mutableListOf()
}
