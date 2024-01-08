package com.example.homework_19.di

import com.example.homework_19.data.common.ResponseHandler
import com.example.homework_19.data.repository.UserDetailRepositoryImpl
import com.example.homework_19.data.repository.UserListRepositoryImpl
import com.example.homework_19.data.service.UserDetailService
import com.example.homework_19.data.service.UserListService
import com.example.homework_19.domain.repository.UserDetailRepository
import com.example.homework_19.domain.repository.UserListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

//    @Singleton
//    @Provides
//    fun provideUserRepository(
//        userListService: UserListService,
//        userDetailService: UserDetailService,
//        responseHandler: ResponseHandler
//    ): UserRepository {
//        return UserRepositoryImpl(
//            userListService = userListService,
//            userDetailService = userDetailService,
//            responseHandler = responseHandler
//        )
//    }

    @Singleton
    @Provides
    fun provideUserListRepository(
        userListService: UserListService,
        responseHandler: ResponseHandler
    ): UserListRepository {
        return UserListRepositoryImpl(
            userListService = userListService,
            responseHandler = responseHandler
        )
    }

    @Singleton
    @Provides
    fun provideUserDetailRepository(
        userDetailService: UserDetailService,
        responseHandler: ResponseHandler
    ): UserDetailRepository {
        return UserDetailRepositoryImpl(
            userDetailService = userDetailService,
            responseHandler = responseHandler
        )
    }
}