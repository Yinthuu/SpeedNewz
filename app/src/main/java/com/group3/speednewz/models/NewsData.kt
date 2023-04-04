package com.group3.speednewz.models

import java.io.Serializable

data class NewsData(
    val imageURL: String,
    val title: String,
    val favorites: Boolean,
    val content: String,
) : Serializable
