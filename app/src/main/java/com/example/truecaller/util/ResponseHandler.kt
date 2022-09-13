package com.example.truecaller.util

import retrofit2.HttpException

open class ResponseHandler {
    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleException(e: Exception): Resource<T> {
        return when (e) {
            is HttpException -> Resource.error(null, getErrorMessage(e.code()))
            else -> Resource.error(null, getErrorMessage(Int.MAX_VALUE))
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            401 -> "Not authorized"
            else -> "Error"
        }
    }
}