package com.example.homework_19.domain.repository

import com.example.homework_19.data.common.Resource
import com.example.homework_19.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface UserDetailRepository {
suspend fun getUserDetail(id: Int): Flow<Resource<UserDetail>>
}

