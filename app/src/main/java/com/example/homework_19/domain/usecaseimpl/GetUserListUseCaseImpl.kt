package com.example.homework_19.domain.usecaseimpl

import com.example.homework_19.data.common.Resource
import com.example.homework_19.domain.model.UserList
import com.example.homework_19.domain.repository.UserListRepository
import com.example.homework_19.domain.usecase.GetUserListUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//class GetUserListUseCaseImpl @Inject constructor(
//    private val userListRepository: UserListRepository
//) : GetUserListUseCase {
//    override suspend fun getUserList(): Flow<Resource<UserList>> {
//        return userListRepository.getUserList()
//    }
//}
class GetUserListUseCaseImpl @Inject constructor(
    private val userListRepository: UserListRepository
) : GetUserListUseCase {
    override suspend fun execute(): Flow<Resource<List<UserList>>> {
        return userListRepository.getUserList()
    }
}