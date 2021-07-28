package com.example.newssearch.data

import com.example.newssearch.model.NewsSearchResponse
import retrofit2.Response
import retrofit2.Retrofit

class NewsRepository(retrofit: Retrofit) {

    private val newsAPI = retrofit.create(NewsAPI::class.java)

    suspend fun searchNews(
        query: String,
        apiKey: String,
        page: Int
    ): Response<NewsSearchResponse> {
        return newsAPI.searchNews(query, apiKey, page, 20)
    }
}