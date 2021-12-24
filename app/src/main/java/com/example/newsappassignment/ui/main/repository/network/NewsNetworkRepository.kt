package com.example.newsappassignment.ui.main.repository.network

import com.example.newsappassignment.data.model.NewsResponse

interface NewsNetworkRepository {
    suspend fun fetchLatestNews(): NewsResponse
}