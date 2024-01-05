package com.example.homework_19.data.mapper

import com.example.homework_19.data.model.UserDetailDto
import com.example.homework_19.domain.model.UserDetail

fun UserDetailDto.toDomain(): UserDetail {
    return UserDetail(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}