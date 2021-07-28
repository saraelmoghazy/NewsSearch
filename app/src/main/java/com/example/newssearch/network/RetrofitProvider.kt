package com.example.newssearch.network;

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Sara Elmoghazy.
 */
object RetrofitProvider {

    private const val BASE_URL = "https://newsapi.org/v2/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .client(provideOkHttpClient(provideLoggingInterceptor()))
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val b = OkHttpClient.Builder()
        b.addInterceptor(loggingInterceptor)
        return b.build()
    }
}