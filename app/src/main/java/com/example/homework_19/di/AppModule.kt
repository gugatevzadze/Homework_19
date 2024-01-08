package com.example.homework_19.di

import com.example.homework_19.data.common.ResponseHandler
import com.example.homework_19.data.service.UserDetailService
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
object AppModule {
    @Provides
    @Singleton
    fun provideResponseHandler(): ResponseHandler {
        return ResponseHandler()
    }

    private const val BASE_URL_LIST = "https://run.mocky.io/"
    private const val BASE_URL_DETAIL = "https://reqres.in/"

    @Singleton
    @Provides
    @Named("ListRetrofit")
    fun provideListRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_LIST)
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
    @Named("DetailRetrofit")
    fun provideDetailRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_DETAIL)
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
    fun provideUserListService(
        @Named("ListRetrofit") listRetrofit: Retrofit
    ): UserListService {
        return listRetrofit.create(UserListService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserDetailService(
        @Named("DetailRetrofit") detailRetrofit: Retrofit
    ): UserDetailService {
        return detailRetrofit.create(UserDetailService::class.java)
    }
}