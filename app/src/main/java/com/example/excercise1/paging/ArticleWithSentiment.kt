package com.example.excercise1.paging

import com.example.excercise1.model.Article
import com.example.excercise1.utils.SentimentResult

data class ArticleWithSentiment(
    val article: Article,
    val sentiment: SentimentResult
)