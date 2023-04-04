package com.group3.speednewz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.group3.speednewz.models.NewsData

class FavoritesFragment : Fragment() {

    private val newsAdapter by lazy {
        NewsAdapter(layoutInflater, GlideImageLoader(this.context))
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        val newsView = view.findViewById<RecyclerView>(R.id.news_recycler_view)
        newsView.adapter = newsAdapter
        newsView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        newsAdapter.setFavoritesData(listOf(
            NewsData(
                "https://cdn2.thecatapi.com/images/DBmIBhhyv.jpg",
                "Title 1",
                true
            ),
            NewsData(
                "https://cdn2.thecatapi.com/images/KJF8fB_20.jpg",
                "Title 2",
                false
            ),
            NewsData(
                "https://cdn2.thecatapi.com/images/DBmIBhhyv.jpg",
                "Title 3",
                true
            ),
            NewsData(
                "https://cdn2.thecatapi.com/images/KJF8fB_20.jpg",
                "Title 4",
                false
            ),
        ))
        return view
    }
}