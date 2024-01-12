package com.example.homework_19.presentation.state.detail

import com.example.homework_19.presentation.model.User

data class UserDetailState(
    val details: User? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
