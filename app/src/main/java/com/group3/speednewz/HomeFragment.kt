package com.group3.speednewz

import android.annotation.SuppressLint
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
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class HomeFragment( ) : Fragment() {

    private val newsAdapter by lazy {
        NewsAdapter(layoutInflater, GlideImageLoader(this.context))
    }
    var db: SQLiteDatabase? = null

    private lateinit var recyclerView: RecyclerView
    @SuppressLint("Range")
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

        recyclerView = view.findViewById(R.id.news_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val db = requireActivity().openOrCreateDatabase("SpeedyNewz", Context.MODE_PRIVATE, null)

        val cursor = db.rawQuery("SELECT * FROM news", null)
        val count = cursor.count

        val newsList = mutableListOf<NewsData>()


        if (cursor.moveToFirst()) {
            do {

                val imageURL = cursor.getString(cursor.getColumnIndex("imageURL"))
                val title = cursor.getString(cursor.getColumnIndex("title"))
                println(imageURL+"-----------"+title)
                val content = cursor.getString(cursor.getColumnIndex("content"))
                val favorites = cursor.getInt(cursor.getColumnIndex("favorites"))
                // convert INTEGER  to  Boolean
                val isFavorites = if (favorites == 1) true else false
                val news = NewsData(imageURL, title, isFavorites, content)
                newsList.add(news)
            } while (cursor.moveToNext())
        }



        val adapter = NewsAdapter(layoutInflater, GlideImageLoader(requireContext()))
        adapter.setData(newsList)
        recyclerView.adapter = adapter

        cursor.close()
        db.close()

        return view
    }
}