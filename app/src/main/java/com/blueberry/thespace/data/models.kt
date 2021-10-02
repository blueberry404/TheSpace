package com.blueberry.thespace.data

data class HomeExplore(val title: String, val imageURL: String)

data class PictureOfDay(
    val copyright: String,
    val date: String,
    val explanation: String,
    val hdurl: String,
    val media_type: String,
    val title: String,
    val url: String
)