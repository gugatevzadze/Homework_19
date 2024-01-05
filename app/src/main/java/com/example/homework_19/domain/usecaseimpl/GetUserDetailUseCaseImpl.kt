package com.example.homework_19.domain.usecaseimpl

import com.example.homework_19.data.common.Resource
import com.example.homework_19.domain.model.UserDetail
import com.example.homework_19.domain.repository.UserDetailRepository
import com.example.homework_19.domain.usecase.GetUserDetailUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//class GetUserDetailUseCaseImpl @Inject constructor(
//    private val userDetailRepository: UserDetailRepository
//) : GetUserDetailUseCase {
//    override suspend fun getUserDetail(id: Int): Flow<Resource<UserDetail>> {
//        return userDetailRepository.getUserDetail(id)
//    }
//}
class GetUserDetailUseCaseImpl @Inject constructor(
    private val userDetailRepository: UserDetailRepository
) : GetUserDetailUseCase {
    override suspend fun execute(userId: Int): Flow<Resource<UserDetail>> {
        return userDetailRepository.getUserDetail(userId)
    }
}