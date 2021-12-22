package com.example.newsappassignment.ui.main.repository

import com.example.newsappassignment.utils.NetworkResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class BaseRepository {
    suspend inline fun <reified T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        noinline apiCall: suspend () -> T
    ): NetworkResource<T> {
        return withContext(dispatcher) {
            try {
                NetworkResource.success(apiCall.invoke())
            } catch (throwable: Throwable) {
                processForError(throwable)
            }
        }
    }

    suspend inline fun <reified T> processForError(
        throwable: Throwable
    ): NetworkResource<T> {
        return NetworkResource.error(throwable, null)
    }
}