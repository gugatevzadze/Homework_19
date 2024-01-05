package com.example.homework_19.data.service

import com.example.homework_19.data.model.UserListDto
import retrofit2.Response
import retrofit2.http.GET

interface UserListService {
    @GET("/v3/7ec14eae-06bf-4f6d-86d2-ac1b9df5fe3d")
    suspend fun getUserList():Response<List<UserListDto>>
}