package com.example.newsapp.ui.repository

import com.example.newsapp.ui.api.RetrofitInstance
import com.example.newsapp.ui.db.ArticleDatabase

class NewsRepository(
    val db : ArticleDatabase
){
    suspend fun getBreakingNews(countryCode : String , pageNumber : Int) =
            RetrofitInstance.api.getBreakingNews(countryCode , pageNumber)
}