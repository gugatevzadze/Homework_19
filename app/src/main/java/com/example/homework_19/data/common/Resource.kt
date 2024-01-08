package com.example.homework_19.data.common

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val errorMessage: String) : Resource<Nothing>()
    data class Loading(val loading: Boolean) : Resource<Nothing>()
}


