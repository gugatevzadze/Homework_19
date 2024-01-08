package com.example.homework_19.data.service

import com.example.homework_19.data.model.UserDetailResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserDetailService {
    @GET("/api/users/{id}")
    suspend fun getUserDetail(@Path("id") id: Int): Response<UserDetailResponseDto>
}
