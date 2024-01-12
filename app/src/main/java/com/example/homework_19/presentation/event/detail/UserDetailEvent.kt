package com.example.homework_19.presentation.event.detail

sealed class UserDetailEvent{
    data class GetUserDetail(val userId: Int) : UserDetailEvent()
}
