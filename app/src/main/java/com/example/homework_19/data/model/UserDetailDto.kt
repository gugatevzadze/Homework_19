package com.example.homework_19.data.model

import com.squareup.moshi.Json

data class UserDetailDto(
    val id: Int,
    val email: String,
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "last_name")
    val lastName: String,
    val avatar: String
)