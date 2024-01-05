package com.example.homework_19.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_19.data.common.Resource
import com.example.homework_19.domain.model.UserDetail
import com.example.homework_19.domain.usecase.GetUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class DetailViewModel @Inject constructor(
//    private val getUserDetailUseCase: GetUserDetailUseCase
//) : ViewModel() {
//}
//@HiltViewModel
//class DetailViewModel @Inject constructor(
//    private val getUserDetailUseCase: GetUserDetailUseCase
//) : ViewModel() {
//
//    private val _userDetail = MutableStateFlow<Resource<UserDetail>>(Resource.Loading(false))
//    val userDetail: StateFlow<Resource<UserDetail>> get() = _userDetail
//
//    init {
//        // Example: Fetch user details on ViewModel initialization
//        viewModelScope.launch {
//            _userDetail.value = getUserDetailUseCase.execute().first()
//        }
//    }
//}
