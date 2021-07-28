package com.example.newssearch.usecase

import com.example.newssearch.data.NewsRepository
import com.example.newssearch.model.NewsSearchResponse
import retrofit2.Response

class GetNewsUseCase(private val newsRepository: NewsRepository) {

    lateinit var query: String

    var page: Int = 1

    suspend fun execute(): Response<NewsSearchResponse> {
        return newsRepository.searchNews("Apple", "f5ea014de5cc4e1c9ee37d2fda335afb", page)
    }
}