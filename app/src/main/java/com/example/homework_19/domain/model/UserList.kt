package com.example.homework_19.domain.model

//data class UserList(
//    val list: List<UserDetail>
//)
data class UserList(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
)