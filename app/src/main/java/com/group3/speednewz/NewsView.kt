package com.group3.speednewz

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.group3.speednewz.models.NewsData
import android.database.sqlite.SQLiteDatabase
import android.util.Log

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

    private val favoritesView: ImageView by lazy {
        containerView.findViewById(R.id.news_favorites)
    }

    fun bindData(newsData: NewsData) {
        imageLoader.loadImage(newsData.imageURL, imageView)
        titleView.text = newsData.title
//        if (newsData.favorites.equals(true)) {
//            favoritesView.setVisibility(View.VISIBLE);
//        } else {
//            favoritesView.setVisibility(View.GONE);
//        }
        // Set the color of the favorites view based on the value of `favorites` in `newsData`
        favoritesView.setImageResource(if (newsData.favorites) R.drawable.favorites_selected else R.drawable.favorites)

        // Add an OnClickListener to `favoritesView`
//        favoritesView.setOnClickListener {
//            newsData.favorites = !newsData.favorites // toggle the value of `favorites`
//            favoritesView.setImageResource(
//                if (newsData.favorites) {
//                    R.drawable.favorites_selected
//                   //update in database
//
//                }
//                else{
//                    R.drawable.favorites
//                    //update in database
//                }
//
//            )
//        }
        favoritesView.setOnClickListener {it
            val db = it.context.openOrCreateDatabase("SpeedyNewz", Context.MODE_PRIVATE, null)
            Log.d("news view", "clicked")

            newsData.favorites = !newsData.favorites // toggle the value of `favorites`
            favoritesView.setImageResource(
                if (newsData.favorites) {
                    R.drawable.favorites_selected
                } else {
                    R.drawable.favorites
                }
            )
            // update the `favorites` value in the database
            val sql = "UPDATE news SET favorites = ${if (newsData.favorites) 1 else 0} WHERE title = '${newsData.title}'"
            db?.execSQL(sql) //this line
            val cursor = db?.rawQuery("SELECT * FROM news WHERE favorites = 1", null)
            cursor?.count?.toString()?.let { it1 -> Log.d("news view", it1) }


            // check if the update was successful
//            val numRowsAffected = db?.changes() ?: 0
//            if (numRowsAffected == 1) {
//                // the update was successful
//                // you can log a message or update the UI to reflect the new value
//                println("the update was successful");
//            } else {
//                // the update was not successful
//                // you can log an error message or handle the error as appropriate
//                println("the update was not successful");
//            }
        }
    }
}
