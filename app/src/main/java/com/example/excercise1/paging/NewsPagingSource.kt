package com.example.excercise1.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.excercise1.api.NewsApiService
import com.example.excercise1.utils.SentimentAnalyzer

class NewsPagingSource(
    private val service: NewsApiService,
    private val apiKey: String,
    private val analyzer: SentimentAnalyzer
) : PagingSource<Int, ArticleWithSentiment>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleWithSentiment> {
        return try {
            val page = params.key ?: 1

            val response = service.getArticles(
                query = "android",
                pageSize = params.loadSize,
                page = page,
                apiKey = apiKey
            )

            val data = response.articles.map {
                val sentiment = analyzer.analyze(it.content ?: it.title)
                ArticleWithSentiment(it, sentiment)
            }

            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page * params.loadSize < response.totalResults) page + 1 else null
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticleWithSentiment>): Int? = null
}