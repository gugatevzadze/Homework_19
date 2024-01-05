package com.example.homework_19.domain.usecase

import com.example.homework_19.data.common.Resource
import com.example.homework_19.domain.model.UserList
import kotlinx.coroutines.flow.Flow

//interface GetUserListUseCase {
//    suspend fun getUserList(): Flow<Resource<UserList>>
//}
interface GetUserListUseCase {
    suspend fun execute(): Flow<Resource<List<UserList>>>
}