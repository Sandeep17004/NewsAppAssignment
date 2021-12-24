package com.example.newsappassignment.api

import com.example.newsappassignment.data.model.NewsResponse
import com.example.newsappassignment.utils.Config
import retrofit2.http.GET

interface NewsApiInterface {
    @GET("top-headlines?country=in&apiKey=${Config.api_key}")
    suspend fun fetchHeadLines(): NewsResponse

}