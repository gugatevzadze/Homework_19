package com.example.homework_19.di

import com.example.homework_19.data.service.UserListService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserDetailApiModule {
    private const val BASE_URL = "https://reqres.in/api/"

    @Singleton
    @Provides
    @Named("userDetailRetrofit")
    fun provideRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDetailService(@Named("userDetailRetrofit") retrofit: Retrofit): UserListService {
        return retrofit.create(UserListService::class.java)
    }
}