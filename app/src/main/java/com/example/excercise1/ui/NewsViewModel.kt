package com.example.excercise1.ui

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.excercise1.api.NewsApiService
import com.example.excercise1.paging.NewsPagingSource
import com.example.excercise1.utils.SentimentAnalyzer
import com.example.excercise1.BuildConfig
class NewsViewModel(
    private val service: NewsApiService,
    private val analyzer: SentimentAnalyzer
) : ViewModel() {

    val articles = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            NewsPagingSource(service, "dda42e5ed903485cbfe67be3f5b0472f", analyzer)
        }
    ).flow
}