package com.example.homework_19.data.mapper

import com.example.homework_19.data.model.UserListDto
import com.example.homework_19.domain.model.UserList

fun UserListDto.toDomain(): UserList {
    return UserList(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}