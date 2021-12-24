package com.example.newsappassignment.utils

import android.annotation.SuppressLint
import android.text.format.DateUtils
import com.example.newsappassignment.data.model.NewsResponse
import com.example.newsappassignment.storage.entity.NewsArticleDb
import com.example.newsappassignment.utils.Constants.Companion.SERVER_TIME_FORMAT
import com.example.newsappassignment.utils.Constants.Companion.TIMEZONE
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class CommonUtility {
    companion object {
        fun convertNewsArticleToDatabaseDao(article: List<NewsResponse.NewsArticle>): List<NewsArticleDb> {
            return article.map { it.newsArticleToDao() }
        }

        private fun NewsResponse.NewsArticle.newsArticleToDao(): NewsArticleDb {
            return NewsArticleDb(
                author = author,
                title = title,
                description = description,
                url = url,
                urlToImage = urlToImage,
                publishedAt = publishedAt,
                content = content,
                source = NewsArticleDb.Source(source.id, source.name)
            )
        }

        @SuppressLint("SimpleDateFormat")
        fun formatTime(serverTime: String): String {
            var timeAgo = ""
            val sdf = SimpleDateFormat(SERVER_TIME_FORMAT, Locale.ENGLISH)
            sdf.timeZone = TimeZone.getTimeZone(TIMEZONE)
            try {
                val time: Long = sdf.parse(serverTime).time
                val now = System.currentTimeMillis()
                timeAgo = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)
                    .toString()
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return timeAgo
        }
    }
}