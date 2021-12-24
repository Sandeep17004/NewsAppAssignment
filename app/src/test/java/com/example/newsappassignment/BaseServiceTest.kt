package com.example.newsappassignment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.newsappassignment.network.CoroutinesTestRule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
abstract class BaseServiceTest {
    /**
     * To make suspended call to run in blocking part so that it will run in only one instance of Coroutines
     */
    @get:Rule
    var coroutineTestRule = CoroutinesTestRule()

    /**
     *
     * InstantTaskExecutorRule to make call of suspend function otherwise we will get the failed to invoke
     * suspend method error and Test case will not run
     */
    @Rule
    @JvmField
    val instantExecutor = InstantTaskExecutorRule()

    companion object {
        /**
         * To get the instance of Retrofit with MockWebServer attached so that we can mock req and response
         * on api test case
         */
        fun getRetrofitBuilderTesting(mockWebServer: MockWebServer): Retrofit {
            val httpClient = OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                )
            return Retrofit.Builder()
                .baseUrl(mockWebServer.url("/").toString())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build()).build()
        }
    }
}