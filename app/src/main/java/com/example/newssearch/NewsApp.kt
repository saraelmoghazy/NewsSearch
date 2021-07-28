package com.example.newssearch

import android.app.Application
import com.example.newssearch.data.NewsRepository
import com.example.newssearch.network.RetrofitProvider
import com.example.newssearch.usecase.GetNewsUseCase
import com.example.newssearch.viewmodel.NewsViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit


/**
 * Created by Sara Elmoghazy.
 */
class NewsApp : Application(), KodeinAware {

    private val retrofitModule = Kodein.Module(name = "Retrofit") {
        bind<Retrofit>() with singleton { RetrofitProvider.getInstance() }
    }

    override val kodein by Kodein.lazy {
        import(retrofitModule)
        bind() from singleton { NewsRepository(instance()) }
        bind() from provider {
            GetNewsUseCase(
                instance()
            )
        }
        bind() from provider {
            NewsViewModelFactory(instance())
        }

    }
}