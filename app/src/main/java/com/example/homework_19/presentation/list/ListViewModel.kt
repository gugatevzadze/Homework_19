package com.example.homework_19.presentation.list

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_19.data.common.Resource
import com.example.homework_19.domain.model.UserList
import com.example.homework_19.domain.usecase.GetUserListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class ListViewModel @Inject constructor(
//    private val getUserListUseCase: GetUserListUseCase
//) : ViewModel() {
//
//    private val _userList = MutableStateFlow<Resource<UserList>>(Resource.Loading(false))
//    val userList: StateFlow<Resource<UserList>> get() = _userList
//
//    private val _navigateToUserDetail = MutableSharedFlow<Int?>()
//    val navigateToUserDetail: SharedFlow<Int?> get() = _navigateToUserDetail
//
//    fun getUserList() {
//        viewModelScope.launch {
//            _userList.value = getUserListUseCase.execute().first()
//        }
//    }
//
//    fun navigateToUserDetail(userId: Int) {
//        viewModelScope.launch {
//            _navigateToUserDetail.emit(userId)
//        }
//    }
//}
@HiltViewModel
class ListViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase
) : ViewModel() {

    private val _userList = MutableStateFlow<Resource<List<UserList>>>(Resource.Loading(false))
    val userList: StateFlow<Resource<List<UserList>>> get() = _userList

    private val _navigateToUserDetail = MutableSharedFlow<UserList?>()
    val navigateToUserDetail: SharedFlow<UserList?> get() = _navigateToUserDetail

    fun getUserList() {
        viewModelScope.launch {
            d("ListViewModel", "getUserList() called")
            _userList.value = getUserListUseCase.execute().first()
        }
    }

    fun navigateToUserDetail(user: UserList) {
        viewModelScope.launch {
            _navigateToUserDetail.emit(user)
        }
    }
}
