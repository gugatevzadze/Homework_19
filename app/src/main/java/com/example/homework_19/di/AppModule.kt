package com.example.homework_19.di

import com.example.homework_19.data.common.ResponseHandler
import com.example.homework_19.data.service.UserApiService
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
    @Named("MockyRetrofit")
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
    @Named("ReqresRetrofit")
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
    @Named("MockyService")
    fun provideUserListService(@Named("MockyRetrofit")listRetrofit: Retrofit
    ): UserApiService {
        return listRetrofit.create(UserApiService::class.java)
    }

    @Singleton
    @Provides
    @Named("ReqresService")
    fun provideUserDetailService(@Named("ReqresRetrofit")detailRetrofit: Retrofit
    ): UserApiService {
        return detailRetrofit.create(UserApiService::class.java)
    }
}