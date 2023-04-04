package com.group3.speednewz

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader(private val context: Context?) : ImageLoader {
    override fun loadImage(imageURL: String, imageView: ImageView) {
        if (context != null) {
            Glide.with(context)
                .load(imageURL)
                .centerCrop()
                .into(imageView)
        }
    }
}