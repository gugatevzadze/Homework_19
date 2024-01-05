package com.example.homework_19.data.repository

import com.example.homework_19.data.common.Resource
import com.example.homework_19.data.common.ResponseHandler
import com.example.homework_19.data.mapper.toDomain
import com.example.homework_19.data.service.UserDetailService
import com.example.homework_19.domain.model.UserDetail
import com.example.homework_19.domain.repository.UserDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDetailRepositoryImpl @Inject constructor(
    private val userDetailService: UserDetailService,
    private val responseHandler: ResponseHandler
) : UserDetailRepository {
    override suspend fun getUserDetail(id: Int): Flow<Resource<UserDetail>> {
        return responseHandler.safeApiCall {
            userDetailService.getUserDetail(id = id)
        }.map {
            when (it) {
                is Resource.Success -> Resource.Success(data = it.data.toDomain())
                is Resource.Error -> Resource.Error(errorMessage = it.errorMessage)
                is Resource.Loading -> Resource.Loading(loading = it.loading)
            }
        }
    }
}
//class UserDetailRepositoryImpl @Inject constructor(
//    private val userDetailService: UserDetailService,
//    private val responseHandler: ResponseHandler
//) : UserDetailRepository {
//
//    override suspend fun getUserDetail(id: Int): Flow<Resource<UserDetail>> {
//        return responseHandler.safeApiCall {
//            userDetailService.getUserDetail(id = id)
//        }.map {
//            it.map { data -> data.toDomain() }
//        }
//    }
//}




