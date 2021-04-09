package com.example.newsapp.ui.api

import com.example.newsapp.ui.models.NewsResponse
import com.example.newsapp.ui.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
            @Query("country")
            countryCode: String  = "in",
            @Query("page")
            pageNumber: Int = 1,
            @Query("api_key")
            apikey: String = API_KEY
    ) : Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
            @Query("q")
            countryCode: String,
            @Query("page")
            pageNumber: Int = 1,
            @Query("api_key")
            apikey: String = API_KEY
    ) : Response<NewsResponse>

}