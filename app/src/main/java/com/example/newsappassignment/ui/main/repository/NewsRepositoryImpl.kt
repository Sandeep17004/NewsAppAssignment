package com.example.newsappassignment.ui.main.repository

import com.example.newsappassignment.data.model.NewsResponse
import com.example.newsappassignment.ui.main.repository.network.NewsNetworkRepository
import com.example.newsappassignment.utils.NetworkResource
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Named

class NewsRepositoryImpl @Inject constructor(
    @Named("IO") private var dispatcher: CoroutineDispatcher,
    @Named("MAIN") private var main: CoroutineDispatcher,
    private val network: NewsNetworkRepository
) : BaseRepository(), NewsRepository {
    override suspend fun fetchLatestNews(): NetworkResource<List<NewsResponse>> {
        return safeApiCall(dispatcher) { network.fetchLatestNews() }
    }
}