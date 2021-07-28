package com.example.newssearch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.newssearch.NewsPagingDataSource
import com.example.newssearch.model.ArticlesItem
import com.example.newssearch.usecase.GetNewsUseCase
import kotlinx.coroutines.flow.Flow

class NewsViewModel(val newsUseCase: GetNewsUseCase) : ViewModel() {

    var characters: Flow<PagingData<ArticlesItem>> =
        Pager(config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { NewsPagingDataSource(newsUseCase) }
        ).flow.cachedIn(viewModelScope)
}