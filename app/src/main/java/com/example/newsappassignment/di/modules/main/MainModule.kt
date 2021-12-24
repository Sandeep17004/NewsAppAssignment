package com.example.newsappassignment.di.modules.main

import com.example.newsappassignment.storage.AppDatabase
import com.example.newsappassignment.storage.NewsArticleDao
import com.example.newsappassignment.ui.main.MainFragment
import com.example.newsappassignment.ui.main.adapter.NewsArticlesAdapter
import com.example.newsappassignment.ui.main.repository.NewsRepository
import com.example.newsappassignment.ui.main.repository.NewsRepositoryImpl
import com.example.newsappassignment.ui.main.repository.network.NewsNetworkRepository
import com.example.newsappassignment.ui.main.repository.network.NewsNetworkRepositoryImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
class MainModule {

    @Provides
    @Named("IO")
    fun provideIOCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @Named("MAIN")
    fun provideMainCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    @Provides
    fun provideNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository {
        return newsRepositoryImpl
    }

    @Provides
    fun provideNewsNetworkRepository(network: NewsNetworkRepositoryImpl): NewsNetworkRepository {
        return network
    }

    @Provides
    fun provideNewsDao(db: AppDatabase): NewsArticleDao {
        return db.newsArticleDao()
    }

    @Provides
    fun provideMainFragment(): MainFragment {
        return MainFragment()
    }

    @Provides
    fun provideNewsArticleAdapter(): NewsArticlesAdapter {
        return NewsArticlesAdapter()
    }
}