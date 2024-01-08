package com.example.homework_19.data.repository

import com.example.homework_19.data.common.Resource
import com.example.homework_19.data.common.ResponseHandler
import com.example.homework_19.data.common.mapToDomain
import com.example.homework_19.data.mapper.toDomain
import com.example.homework_19.data.service.UserDetailService
import com.example.homework_19.domain.model.UserEntity
import com.example.homework_19.domain.repository.UserDetailRepository
import kotlinx.coroutines.flow.Flow

class UserDetailRepositoryImpl(
    private val userDetailService: UserDetailService,
    private val responseHandler: ResponseHandler
) : UserDetailRepository {
    override suspend fun getUserDetail(userId: Int): Flow<Resource<UserEntity>> {
        return responseHandler.safeApiCall {
            userDetailService.getUserDetail(userId)
        }.mapToDomain { userDetailResponseDto ->
            userDetailResponseDto.data.toDomain()
        }
    }
}