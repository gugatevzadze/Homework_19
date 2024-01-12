package com.example.homework_19.presentation.state.list

import com.example.homework_19.presentation.model.User

data class UserListState(
    val users: List<User>? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)