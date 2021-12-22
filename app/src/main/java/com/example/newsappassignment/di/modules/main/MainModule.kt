package com.example.newsappassignment.di.modules.main

import com.example.newsappassignment.api.NewsApiInterface
import com.example.newsappassignment.ui.main.repository.NewsRepository
import com.example.newsappassignment.ui.main.repository.NewsRepositoryImpl
import com.example.newsappassignment.ui.main.repository.network.NewsNetworkRepository
import com.example.newsappassignment.ui.main.repository.network.NewsNetworkRepositoryImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import javax.inject.Named

@Module
class MainModule {
    val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
    val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideNewsApiInterface(retrofit: Retrofit): NewsApiInterface {
        return retrofit.create(NewsApiInterface::class.java)
    }

    @Provides
    @Named("IO")
    fun provideIOCoroutineDispatcher(): CoroutineDispatcher {
        return ioDispatcher
    }

    @Provides
    @Named("MAIN")
    fun provideMainCoroutineDispatcher(): CoroutineDispatcher {
        return mainDispatcher
    }

    @Provides
    fun provideNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository {
        return newsRepositoryImpl
    }

    @Provides
    fun provideNewsNetworkRepository(network: NewsNetworkRepositoryImpl): NewsNetworkRepository {
        return network
    }
}