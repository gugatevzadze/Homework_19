package com.example.homework_19.data.service

import com.example.homework_19.data.model.UserDetailResponseDto
import com.example.homework_19.data.model.UserDto
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {
    @GET("/api/users/{id}")
    suspend fun getUserDetail(@Path("id") id: Int): Response<UserDetailResponseDto>

    @GET("/v3/7ec14eae-06bf-4f6d-86d2-ac1b9df5fe3d")
    suspend fun getUserList(): Response<List<UserDto>>

//    @DELETE("/api/users/{id}")
//    suspend fun deleteUser(@Path("id") id: Int): Response<UserDetailResponseDto>
}