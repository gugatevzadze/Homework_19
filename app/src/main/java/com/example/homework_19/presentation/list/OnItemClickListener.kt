package com.example.homework_19.presentation.list

import com.example.homework_19.domain.model.UserList

interface OnItemClickListener {
    fun onItemClick(user: UserList)
}