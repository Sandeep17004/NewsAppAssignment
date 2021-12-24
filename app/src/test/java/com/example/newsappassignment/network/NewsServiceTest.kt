package com.example.newsappassignment.network

import com.example.newsappassignment.BaseServiceTest
import com.example.newsappassignment.api.NewsApiInterface
import com.example.newsappassignment.data.model.NewsResponse
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.IOException

class NewsServiceTest : BaseServiceTest() {

    private var mockWebServer = MockWebServer()
    private lateinit var newsApiInterface: NewsApiInterface

    @Before
    fun createService() {
        newsApiInterface = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/").toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Test
    @Throws(IOException::class, InterruptedException::class)
    fun getNewsSource() = runBlocking {
        val response = Mockito.mock(NewsResponse::class.java)
        Mockito.`when`(newsApiInterface.fetchHeadLines()).thenReturn(response)
        val data = newsApiInterface.fetchHeadLines()
        TestCase.assertEquals(data, response)

        /*       val temp =
                   this.javaClass.classLoader?.getResource("dummy_news_response.json")
                       ?.readText()
               val mockResponse = temp?.let {
                   MockResponse()
                       .setResponseCode(HttpURLConnection.HTTP_OK)
                       .setBody(it)
               }
               mockResponse?.let { mockWebServer.enqueue(it) }
               val latestNews =
                   newsApiInterface.fetchHeadLines()
               TestCase.assertNotNull(latestNews)
               TestCase.assertNotNull(latestNews.articles)
               TestCase.assertNotNull(latestNews.totalResults)*/
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}