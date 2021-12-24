package com.example.newsappassignment.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.newsappassignment.storage.entity.NewsArticleDb

@Dao
interface NewsArticleDao {
    @Insert
    fun insertNewsArticles(articles: List<NewsArticleDb>): List<Long>

    @Query("DELETE FROM news_article")
    fun clearAllNewsArticles()

    @Transaction
    fun clearAndStoreNewsArticles(articles: List<NewsArticleDb>) {
        clearAllNewsArticles()
        insertNewsArticles(articles)
    }

    @Query("SELECT * FROM news_article")
    fun getAllNewsArticles(): List<NewsArticleDb>
}