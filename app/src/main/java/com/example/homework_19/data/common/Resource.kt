package com.example.homework_19.data.common

//sealed class Resource<T> {
//    data class Success<T>(val data: T) : Resource<T>()
//    data class Error<T>(val errorMessage: String) : Resource<T>()
//    data class Loading<T>(val loading: Boolean) : Resource<T>()
//}

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val errorMessage: String) : Resource<T>()
    data class Loading<T>(val loading: Boolean) : Resource<T>()

//    fun <R> map(transform: (T) -> R): Resource<R> {
//        return when (this) {
//            is Success -> Success(data = transform(data))
//            is Error -> Error(errorMessage = errorMessage)
//            is Loading -> Loading(loading = loading)
//        }
//    }
}
