package com.example.newsappassignment.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsappassignment.storage.AppDatabase.Companion.DATABASE_VERSION
import com.example.newsappassignment.storage.entity.NewsArticleDb

@Database(
    entities = [NewsArticleDb::class],
    version = DATABASE_VERSION
)


abstract class AppDatabase : RoomDatabase() {

    abstract fun newsArticleDao(): NewsArticleDao

    companion object {
        const val DATABASE_NAME: String = "news_app_db"
        const val DATABASE_VERSION = 3
    }
}