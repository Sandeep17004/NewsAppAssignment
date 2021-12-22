package com.example.newsappassignment.ui.main.repository

import com.example.newsappassignment.data.model.NewsResponse
import com.example.newsappassignment.utils.NetworkResource

interface NewsRepository {
    suspend fun fetchLatestNews(): NetworkResource<List<NewsResponse>>
}