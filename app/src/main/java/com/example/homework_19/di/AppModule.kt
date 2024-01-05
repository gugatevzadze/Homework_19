package com.example.homework_19.di

import com.example.homework_19.data.common.ResponseHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideResponseHandler(): ResponseHandler {
        return ResponseHandler()
    }
}