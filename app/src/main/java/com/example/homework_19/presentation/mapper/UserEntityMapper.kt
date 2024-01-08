package com.example.homework_19.presentation.mapper

import com.example.homework_19.domain.model.UserEntity
import com.example.homework_19.presentation.model.User


fun UserEntity.toPresentation(): User {
    return User(
        id = id,
        fullName = "$firstName $lastName",
        email = email,
        avatar = avatar
    )
}
