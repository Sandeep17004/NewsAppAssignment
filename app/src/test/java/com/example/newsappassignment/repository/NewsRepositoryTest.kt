package com.example.newsappassignment.repository

import androidx.lifecycle.Observer
import com.example.newsappassignment.BaseServiceTest
import com.example.newsappassignment.data.model.NewsResponse
import com.example.newsappassignment.storage.NewsArticleDao
import com.example.newsappassignment.storage.entity.NewsArticleDb
import com.example.newsappassignment.ui.main.repository.NewsRepository
import com.example.newsappassignment.ui.main.viewmodels.NewsViewModel
import com.example.newsappassignment.utils.NetworkResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class NewsRepositoryTest : BaseServiceTest() {

    @Mock
    lateinit var newsRepository: NewsRepository

    @Mock
    lateinit var dao: NewsArticleDao
    lateinit var viewModel: NewsViewModel

    @Mock
    lateinit var observerNewsLiveData: Observer<NetworkResource<List<NewsArticleDb>>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = NewsViewModel(
            coroutineTestRule.testDispatcher,
            newsRepository,
            dao
        )
        viewModel.getNewsHeadlines().observeForever(observerNewsLiveData)
        Dispatchers.setMain(coroutineTestRule.testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadNewsHeadlinesLiveData() {
        viewModel.getNewsHeadlines()
    }

    @Test
    fun loadNewsHeadlinesDataNotNull() = runBlockingTest {
        viewModel.getNewsHeadlines()
    }

    @Test
    fun testViewModelNotNull() = runBlockingTest {
        Assert.assertNotNull(viewModel)
    }

    @Test
    fun testLoadAllNewsHeadlinesFromServerResponseOnSuccess() = runBlockingTest {
        val viewModelResponse = Mockito.mock(NewsResponse::class.java)
        val data = NetworkResource.success(viewModelResponse)
        Mockito.`when`(
            newsRepository.fetchLatestNews()
        ).thenReturn(data)

        viewModel.loadNewsHeadlines()
        Mockito.verify(observerNewsLiveData).onChanged(NetworkResource.loading(null))
    }

}