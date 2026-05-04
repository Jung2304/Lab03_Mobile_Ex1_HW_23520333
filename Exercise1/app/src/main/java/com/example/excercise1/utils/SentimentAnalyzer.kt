package com.example.excercise1.utils

class SentimentAnalyzer {

    fun analyze(text: String): SentimentResult {
        val lower = text.lowercase()

        val positiveWords = listOf("good", "great", "love", "excellent", "happy", "nice")
        val negativeWords = listOf("bad", "terrible", "hate", "sad", "awful")

        val pos = positiveWords.count { lower.contains(it) }.toFloat()
        val neg = negativeWords.count { lower.contains(it) }.toFloat()

        return SentimentResult(
            positive = pos,
            negative = neg
        )
    }
}

data class SentimentResult(
    val positive: Float,
    val negative: Float
)