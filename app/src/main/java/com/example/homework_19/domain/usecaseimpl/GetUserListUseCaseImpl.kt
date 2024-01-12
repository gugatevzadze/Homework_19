package com.example.homework_19.domain.usecaseimpl

import com.example.homework_19.data.common.Resource
import com.example.homework_19.domain.model.UserEntity
import com.example.homework_19.domain.repository.UserRepository
import com.example.homework_19.domain.usecase.GetUserListUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserListUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    GetUserListUseCase {
    override suspend operator fun invoke(): Flow<Resource<List<UserEntity>>> {
        return userRepository.getUserList()
    }
}