package com.example.homework_19.presentation.model

data class User(
    val id: Int,
    val fullName: String,
    val email: String,
    val avatar: String,
    var isSelected: Boolean = false
)