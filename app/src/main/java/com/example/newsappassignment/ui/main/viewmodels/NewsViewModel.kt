package com.example.newsappassignment.ui.main.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappassignment.storage.NewsArticleDao
import com.example.newsappassignment.storage.entity.NewsArticleDb
import com.example.newsappassignment.ui.main.repository.NewsRepository
import com.example.newsappassignment.utils.CommonUtility.Companion.convertNewsArticleToDatabaseDao
import com.example.newsappassignment.utils.NetworkResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class NewsViewModel @Inject constructor(
    @Named("IO") private var ioDispatcher: CoroutineDispatcher,
    private var repository: NewsRepository,
    private var newsDao: NewsArticleDao

) : ViewModel() {
    private var newsLiveData = MutableLiveData<NetworkResource<List<NewsArticleDb>>>()

    fun getNewsHeadlines(): MutableLiveData<NetworkResource<List<NewsArticleDb>>> {
        return newsLiveData
    }

    fun loadNewsHeadlines() {
        newsLiveData.postValue(NetworkResource.loading())
        viewModelScope.launch {
            withContext(ioDispatcher) {
                val response = repository.fetchLatestNews()
                response.data?.let {
                    newsDao.clearAndStoreNewsArticles(convertNewsArticleToDatabaseDao(it.articles))
                }
                val finalResponse = newsDao.getAllNewsArticles()
                finalResponse.let {
                    newsLiveData.postValue(NetworkResource.success(it))
                }
            }
        }
    }
}