package com.example.newsappassignment.ui.main.repository.network

import com.example.newsappassignment.api.NewsApiInterface
import com.example.newsappassignment.data.model.NewsResponse
import javax.inject.Inject

class NewsNetworkRepositoryImpl @Inject constructor(private var api: NewsApiInterface) :
    NewsNetworkRepository {
    override suspend fun fetchLatestNews(): NewsResponse {
        return api.fetchHeadLines()
    }
}