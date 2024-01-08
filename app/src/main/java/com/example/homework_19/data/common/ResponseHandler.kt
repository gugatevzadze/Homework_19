package com.example.homework_19.data.common

import android.util.Log
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class ResponseHandler {
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>) = flow {
        emit(Resource.Loading(loading = true))
        try {
            val response = call()

            Log.d("ResponseHandler", "Response received:")
            Log.d("ResponseHandler", "Status Code: ${response.code()}")
            Log.d("ResponseHandler", "Headers: ${response.headers()}")

            val body = response.body()
            if (response.isSuccessful && body != null) {
                Log.d("ResponseHandler", "Body: $body")
                emit(Resource.Success(data = body))
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Error Occurred"
                Log.e("ResponseHandler", "Error: $errorMessage")

                val errorBody = response.errorBody()?.string()
                Log.e("ResponseHandler", "Error Body: $errorBody")

                emit(Resource.Error(errorMessage = errorMessage))
            }
        } catch (e: Exception) {
            Log.e("ResponseHandler", "Exception in safeApiCall: ${e.message}")
            emit(Resource.Error(errorMessage = e.message ?: "Error Occurred"))
        } finally {
            emit(Resource.Loading(loading = false))
        }
    }
}

