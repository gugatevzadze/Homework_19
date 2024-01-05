package com.example.homework_19.data.model

//data class UserListDto (
//    val list: List<UserDetailDto>
//)
data class UserListDto(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
)