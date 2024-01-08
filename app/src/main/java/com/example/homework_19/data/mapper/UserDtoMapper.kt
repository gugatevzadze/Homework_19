package com.example.homework_19.data.mapper

import com.example.homework_19.data.model.UserDto
import com.example.homework_19.domain.model.UserEntity

fun UserDto.toDomain(): UserEntity {
    return UserEntity(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}