package com.example.newsapp.ui.models

import com.example.newsapp.ui.models.Article

data class NewsResponse(
        val articles: List<Article>,
        val status: String,
        val totalResults: Int
)