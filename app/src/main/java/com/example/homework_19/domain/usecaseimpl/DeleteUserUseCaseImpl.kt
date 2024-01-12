package com.example.homework_19.domain.usecaseimpl

import com.example.homework_19.data.common.Resource
import com.example.homework_19.domain.model.UserEntity
import com.example.homework_19.domain.repository.UserRepository
import com.example.homework_19.domain.usecase.DeleteUserUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteUserUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    DeleteUserUseCase {
    override suspend operator fun invoke(userId: Int): Flow<Resource<UserEntity>> {
        return userRepository.deleteUser(userId)
    }
}