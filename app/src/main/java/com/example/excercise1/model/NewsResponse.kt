package com.example.excercise1.model

data class NewsResponse(
    val articles: List<Article>,
    val totalResults: Int
)