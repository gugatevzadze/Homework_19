//package com.example.homework_19.data.repository
//
//import com.example.homework_19.data.common.Resource
//import com.example.homework_19.data.common.ResponseHandler
//import com.example.homework_19.data.common.mapToDomain
//import com.example.homework_19.data.mapper.toDomain
//import com.example.homework_19.data.service.UserDetailService
//import com.example.homework_19.data.service.UserListService
//import com.example.homework_19.domain.model.UserEntity
//import com.example.homework_19.domain.repository.UserRepository
//import kotlinx.coroutines.flow.Flow
//import javax.inject.Inject
//
//class UserRepositoryImpl@Inject constructor(
//    private val userListService: UserListService,
//    private val userDetailService: UserDetailService,
//    private val responseHandler: ResponseHandler
//) : UserRepository {
//
//    override suspend fun getUserList(): Flow<Resource<List<UserEntity>>> {
//        return responseHandler.safeApiCall {
//            userListService.getUserList()
//        }.mapToDomain { userListDto ->
//            userListDto.map { it.toDomain() }
//        }
//    }
//
//    override suspend fun getUserDetail(userId: Int): Flow<Resource<UserEntity>> {
//        return responseHandler.safeApiCall {
//            userDetailService.getUserDetail(userId)
//        }.mapToDomain { userDto ->
//            userDto.toDomain()
//        }
//    }
//}