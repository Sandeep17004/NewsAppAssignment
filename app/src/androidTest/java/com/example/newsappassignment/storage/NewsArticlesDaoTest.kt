package com.example.newsappassignment.storage

import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.newsappassignment.storage.entity.NewsArticleDb
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsArticlesDaoTest {
    lateinit var appDatabase: AppDatabase

    @Before
    fun initDB() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation()
                .targetContext, AppDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun closeDB() {
        appDatabase.close()
    }

    @Test
    @Throws(InterruptedException::class)
    fun insertNewsArticles() {
        val input = listOf(NewsArticleDb(1), NewsArticleDb(2))
        assertThat(appDatabase.newsArticleDao().insertNewsArticles(input), equalTo(listOf(1L, 2L)))
    }

    @Test
    @Throws(InterruptedException::class)
    fun insertNewsArticlesAndRead(): Unit = runBlocking {
        val input = listOf(
            NewsArticleDb(1, "Author1", "Breaking News"),
            NewsArticleDb(2, "Author2", "Football News")
        )
        appDatabase.newsArticleDao().insertNewsArticles(input)
        Assert.assertEquals(input, appDatabase.newsArticleDao().getAllNewsArticles())
    }
}