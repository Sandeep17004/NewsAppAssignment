package com.example.newsappassignment.utils

data class NetworkResource<out T>(
    var status: Status,
    val data: T?,
    val throwable
    : Throwable?
) {
    companion object {
        fun <T> success(data: T?): NetworkResource<T> {
            return NetworkResource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: Throwable? = null, data: T? = null): NetworkResource<T> {
            return NetworkResource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T? = null): NetworkResource<T> {
            return NetworkResource(Status.LOADING, data, null)
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}
