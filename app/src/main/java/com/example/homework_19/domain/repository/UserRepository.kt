//package com.example.homework_19.domain.repository
//
//import com.example.homework_19.data.common.Resource
//import com.example.homework_19.domain.model.UserEntity
//import kotlinx.coroutines.flow.Flow
//
//interface UserRepository {
//    suspend fun getUserList(): Flow<Resource<List<UserEntity>>>
//
//    suspend fun getUserDetail(userId: Int): Flow<Resource<UserEntity>>
//}