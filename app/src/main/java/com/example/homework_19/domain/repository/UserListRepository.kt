package com.example.homework_19.domain.repository

import com.example.homework_19.data.common.Resource
import com.example.homework_19.domain.model.UserList
import kotlinx.coroutines.flow.Flow

interface UserListRepository {
    suspend fun getUserList(): Flow<Resource<List<UserList>>>
}