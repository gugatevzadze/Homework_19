package com.example.homework_19.di

import com.example.homework_19.domain.repository.UserRepository
import com.example.homework_19.domain.usecase.DeleteUserUseCase
import com.example.homework_19.domain.usecase.GetUserDetailUseCase
import com.example.homework_19.domain.usecase.GetUserListUseCase
import com.example.homework_19.domain.usecaseimpl.DeleteUserUseCaseImpl
import com.example.homework_19.domain.usecaseimpl.GetUserDetailUseCaseImpl
import com.example.homework_19.domain.usecaseimpl.GetUserListUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun provideGetUserListUseCase(userRepository: UserRepository): GetUserListUseCase {
        return GetUserListUseCaseImpl(userRepository)
    }

    @Singleton
    @Provides
    fun provideGetUserDetailUseCase(userRepository: UserRepository): GetUserDetailUseCase {
        return GetUserDetailUseCaseImpl(userRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteUserUseCase(userRepository: UserRepository): DeleteUserUseCase {
        return DeleteUserUseCaseImpl(userRepository)
    }
}