package com.group3.speednewz

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.group3.speednewz.models.NewsData

class FavoritesFragment( ) : Fragment() {

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
        println("onCreateView")
        Log.println(Log.DEBUG, "", "fav onCreateView")
// Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        recyclerView = view.findViewById(R.id.news_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)



        val db = requireActivity().openOrCreateDatabase("SpeedyNewz", Context.MODE_PRIVATE, null)


        val cursor = db.rawQuery("SELECT * FROM news WHERE favorites = 1", null)
        val count = cursor.count

        val newsList = mutableListOf<NewsData>()


        if (cursor.moveToFirst()) {
            do {

                val imageURL = cursor.getString(cursor.getColumnIndex("imageURL"))
                val title = cursor.getString(cursor.getColumnIndex("title"))

                val content = cursor.getString(cursor.getColumnIndex("content"))
                val favorites = cursor.getInt(cursor.getColumnIndex("favorites"))
                val dateTime = cursor.getString(cursor.getColumnIndex("dateTime"))
                // convert INTEGER  to  Boolean
                val isFavorites = if (favorites == 1) true else false
                val news = NewsData(imageURL, title, isFavorites, content, dateTime)
                newsList.add(news)
            } while (cursor.moveToNext())
        }



        val adapter = NewsAdapter(layoutInflater, GlideImageLoader(requireContext()))
        adapter.setFavoritesData(newsList)
        recyclerView.adapter = adapter

        cursor.close()
        db.close()

        return view
    }

    override fun onStart() {
        super.onStart()
        Log.println(Log.DEBUG, "", "fav onStart")
        refresh(recyclerView)
    }

    private fun refresh(recyclerView: RecyclerView) {
        val db = requireActivity().openOrCreateDatabase("SpeedyNewz", Context.MODE_PRIVATE, null)
        val cursor = db.rawQuery("SELECT * FROM news", null)
        val newsList = mutableListOf<NewsData>()
        if (cursor.moveToFirst()) {
            do {
                val imageURL = cursor.getString(cursor.getColumnIndexOrThrow("imageURL"))
                val title = cursor.getString(cursor.getColumnIndexOrThrow("title"))
                val content = cursor.getString(cursor.getColumnIndexOrThrow("content"))
                val favorites = cursor.getInt(cursor.getColumnIndexOrThrow("favorites"))
                val dateTime = cursor.getString(cursor.getColumnIndexOrThrow("dateTime"))
                val isFavorites = if (favorites == 1) true else false
                val news = NewsData(imageURL, title, isFavorites, content, dateTime)
                newsList.add(news)
            } while (cursor.moveToNext())
        }
        println("newsList")
        println(newsList.count())
        println(cursor.count)
        (recyclerView.adapter as NewsAdapter).setFavoritesData(newsList)
        (recyclerView.adapter as NewsAdapter).notifyDataSetChanged()
    }
}