package com.example.homework_19.presentation.event.list

import com.example.homework_19.presentation.model.User

sealed class UserListEvent {
    data object GetUserList: UserListEvent()
    data class UserItemClick(val user: User): UserListEvent()
    data class UserItemSelect(val user: User): UserListEvent()
    data class UserItemDeselect(val user: User): UserListEvent()
    data object UserItemDelete: UserListEvent()
}
