package com.example.homework_19.data.repository

import com.example.homework_19.data.common.Resource
import com.example.homework_19.data.common.ResponseHandler
import com.example.homework_19.data.mapper.toDomain
import com.example.homework_19.data.service.UserListService
import com.example.homework_19.domain.model.UserList
import com.example.homework_19.domain.repository.UserListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserListRepositoryImpl @Inject constructor(
    private val userListService: UserListService,
    private val responseHandler: ResponseHandler
) : UserListRepository {
    override suspend fun getUserList(): Flow<Resource<List<UserList>>> {
        return responseHandler.safeApiCall {
            userListService.getUserList()
        }.map { result ->
            when (result) {
                is Resource.Success -> Resource.Success(data = result.data.map { it.toDomain() })
                is Resource.Error -> Resource.Error(errorMessage = result.errorMessage)
                is Resource.Loading -> Resource.Loading(loading = result.loading)
            }
        }
    }
}

//class UserListRepositoryImpl @Inject constructor(
//    private val userListService: UserListService,
//    private val responseHandler: ResponseHandler
//) : UserListRepository {
//    override suspend fun getUserList(): Flow<Resource<List<UserList>>> {
//        return responseHandler.safeApiCall {
//            userListService.getUserList()
//        }.map {
//            when (it) {
//                is Resource.Success -> Resource.Success(data = it.data.map { it.toDomain() })
//                is Resource.Error -> Resource.Error(errorMessage = it.errorMessage)
//                is Resource.Loading -> Resource.Loading(loading = it.loading)
//            }
//        }
//    }
//}
//
//class UserListRepositoryImpl @Inject constructor(
//    private val userListService: UserListService,
//    private val responseHandler: ResponseHandler
//) : UserListRepository {
//    override suspend fun getUserList(): Flow<Resource<UserList>> {
//        return responseHandler.safeApiCall {
//            userListService.getUserList()
//        }.map {
//            it.map { data -> data.toDomain() }
//        }
//    }
//}