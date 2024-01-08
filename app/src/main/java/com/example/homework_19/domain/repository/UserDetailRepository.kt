package com.example.homework_19.domain.repository

import com.example.homework_19.data.common.Resource
import com.example.homework_19.domain.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserDetailRepository {
    suspend fun getUserDetail(userId: Int): Flow<Resource<UserEntity>>
}

