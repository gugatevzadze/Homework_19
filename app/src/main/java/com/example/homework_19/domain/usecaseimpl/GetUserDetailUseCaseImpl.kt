package com.example.homework_19.domain.usecaseimpl

import com.example.homework_19.data.common.Resource
import com.example.homework_19.domain.model.UserEntity
import com.example.homework_19.domain.repository.UserDetailRepository
import com.example.homework_19.domain.usecase.GetUserDetailUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailUseCaseImpl @Inject constructor(
    private val userDetailRepository: UserDetailRepository
) : GetUserDetailUseCase {
    override suspend fun execute(userId: Int): Flow<Resource<UserEntity>> {
        return userDetailRepository.getUserDetail(userId)
    }
}