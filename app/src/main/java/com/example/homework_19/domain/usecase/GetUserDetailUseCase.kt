package com.example.homework_19.domain.usecase

import com.example.homework_19.data.common.Resource
import com.example.homework_19.domain.model.UserEntity
import kotlinx.coroutines.flow.Flow


interface GetUserDetailUseCase {
    suspend operator fun invoke(userId: Int): Flow<Resource<UserEntity>>
}