package com.example.newssearch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newssearch.usecase.GetNewsUseCase


class NewsViewModelFactory(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(
            getNewsUseCase
        ) as T
    }
}