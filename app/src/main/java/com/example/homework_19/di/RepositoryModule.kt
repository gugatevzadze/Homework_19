package com.example.homework_19.di

import com.example.homework_19.data.common.ResponseHandler
import com.example.homework_19.data.repository.UserRepositoryImpl
import com.example.homework_19.data.service.UserApiService
import com.example.homework_19.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        @Named("MockyService") userListService: UserApiService,
        @Named("ReqresService") userDetailService: UserApiService,
        responseHandler: ResponseHandler
    ): UserRepository {
        return UserRepositoryImpl(
            userListService = userListService,
            userDetailService = userDetailService,
            responseHandler = responseHandler
        )
    }
}