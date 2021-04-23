package com.example.newsapp.ui.repository

import com.example.newsapp.ui.api.RetrofitInstance
import com.example.newsapp.ui.db.ArticleDatabase
import com.example.newsapp.ui.models.Article

class NewsRepository(
    val db : ArticleDatabase
){
    suspend fun upsert(article : Article) =
            db.getArticlesDao().upsert(article)
    suspend fun getBreakingNews(countryCode : String , pageNumber : Int) =
            RetrofitInstance.api.getBreakingNews(countryCode , pageNumber)
    suspend fun searchNews(searchQuery : String , pageNumber: Int ) =
            RetrofitInstance.api.searchForNews(searchQuery , pageNumber)

    fun getSavedNews() = db.getArticlesDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticlesDao().deleteArticles(article)

}