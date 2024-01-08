package com.example.homework_19.di

import com.example.homework_19.domain.repository.UserDetailRepository
import com.example.homework_19.domain.repository.UserListRepository
import com.example.homework_19.domain.usecase.GetUserDetailUseCase
import com.example.homework_19.domain.usecase.GetUserListUseCase
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
    fun provideGetUserListUseCase(userListRepository: UserListRepository): GetUserListUseCase {
        return GetUserListUseCaseImpl(userListRepository)
    }

    @Singleton
    @Provides
    fun provideGetUserDetailUseCase(userDetailRepository: UserDetailRepository): GetUserDetailUseCase {
        return GetUserDetailUseCaseImpl(userDetailRepository)
    }
}