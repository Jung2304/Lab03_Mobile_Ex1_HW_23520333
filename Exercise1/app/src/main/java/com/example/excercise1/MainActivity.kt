package com.example.excercise1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.excercise1.adapter.NewsAdapter
import com.example.excercise1.api.RetrofitInstance
import com.example.excercise1.ui.NewsViewModel
import com.example.excercise1.utils.SentimentAnalyzer

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        adapter = NewsAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val viewModel = NewsViewModel(
            RetrofitInstance.api,
            SentimentAnalyzer()
        )

        lifecycleScope.launch {
            viewModel.articles.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}