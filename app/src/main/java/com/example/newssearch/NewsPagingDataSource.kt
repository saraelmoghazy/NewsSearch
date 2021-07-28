package com.example.newssearch

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newssearch.model.ArticlesItem
import com.example.newssearch.usecase.GetNewsUseCase

class NewsPagingDataSource(private val useCase: GetNewsUseCase) :
    PagingSource<Int, ArticlesItem>() {

    override fun getRefreshKey(state: PagingState<Int, ArticlesItem>): Int? {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticlesItem> {
        val pageNumber = params.key ?: 1
        return try {
            useCase.page = pageNumber
            val response = useCase.execute()

            val pagedResponse = response.body()
            val data = pagedResponse?.articles
            var nextPageNumber: Int? = null
            nextPageNumber = pageNumber.inc()
            LoadResult.Page(
                data = data!!,
                prevKey = null,
                nextKey = nextPageNumber
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}