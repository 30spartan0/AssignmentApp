package com.example.assignmentapp.network

import okhttp3.ResponseBody

/*Define success and failure resources, they will wrap the API responses and handle the success
and failure responses */
sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : Resource<Nothing>()
}