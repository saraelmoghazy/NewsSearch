package com.example.newssearch.data

import com.example.newssearch.model.NewsSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String,
        @Query("page") pageNumber: Int,
        @Query("pageSize") pageSize: Int
    ): Response<NewsSearchResponse>

}