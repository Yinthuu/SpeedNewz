package com.group3.speednewz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.group3.speednewz.models.NewsData

class HomeFragment : Fragment() {

    private val newsAdapter by lazy {
        NewsAdapter(layoutInflater, GlideImageLoader(this.context))
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
         //Find a button by Id. Go to next page upon clicking that button
//        view.findViewById<Button>(R.id.button_home)?.setOnClickListener(
//            Navigation.createNavigateOnClickListener(
//                R.id.nav_home_to_content, null
//            )
//        )
        val greetingsView = view.findViewById<TextView>(R.id.greetings)
        greetingsView.text = "Hello ${UserSession.username}!"

        val newsView = view.findViewById<RecyclerView>(R.id.news_recycler_view)
        newsView.adapter = newsAdapter
        newsView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        newsAdapter.setData(listOf(
            NewsData(
                "https://cdn2.thecatapi.com/images/DBmIBhhyv.jpg",
                "Title 1",
                true,
                "The content of news 1"
            ),
            NewsData(
                "https://cdn2.thecatapi.com/images/KJF8fB_20.jpg",
                "Title 2",
            false,
                "The content of news 2"
            ),
            NewsData(
                "https://cdn2.thecatapi.com/images/DBmIBhhyv.jpg",
                "Title 3",
                true,
                "The content of news 3"
            ),
            NewsData(
                "https://cdn2.thecatapi.com/images/KJF8fB_20.jpg",
                "Title 4",
                false,
                "The content of news 4"
            ),
        ))
        return view
    }
}