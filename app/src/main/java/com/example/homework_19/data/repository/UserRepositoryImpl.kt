package com.example.homework_19.data.repository

import com.example.homework_19.data.common.Resource
import com.example.homework_19.data.common.ResponseHandler
import com.example.homework_19.data.mapper.mapToDomain
import com.example.homework_19.data.mapper.toDomain
import com.example.homework_19.data.service.UserApiService

import com.example.homework_19.domain.model.UserEntity
import com.example.homework_19.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class UserRepositoryImpl @Inject constructor(
    @Named("MockyService") private val userListService: UserApiService,
    @Named("ReqresService") private val userDetailService: UserApiService,
    private val responseHandler: ResponseHandler
) : UserRepository {

    override suspend fun getUserList(): Flow<Resource<List<UserEntity>>> {
        return responseHandler.safeApiCall {
            userListService.getUserList()
        }.mapToDomain { userListDto ->
            userListDto.map { it.toDomain() }
        }
    }

    override suspend fun getUserDetail(userId: Int): Flow<Resource<UserEntity>> {
        return responseHandler.safeApiCall {
            userDetailService.getUserDetail(userId)
        }.mapToDomain { userDetailResponseDto ->
            userDetailResponseDto.data.toDomain()
        }
    }

    override suspend fun deleteUser(userId: Int): Flow<Resource<UserEntity>> {
        return responseHandler.safeApiCall {
            userDetailService.deleteUser(userId)
        }.mapToDomain { userDetailResponseDto ->
            userDetailResponseDto.data.toDomain()
        }
    }
}