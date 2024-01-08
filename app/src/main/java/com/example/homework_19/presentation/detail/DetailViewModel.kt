package com.example.homework_19.presentation.detail

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_19.data.common.Resource
import com.example.homework_19.domain.model.UserEntity
import com.example.homework_19.domain.usecase.GetUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase
) : ViewModel() {

    private val _userDetailState = MutableStateFlow<Resource<UserEntity>>(Resource.Loading(false))
    val userDetailState: StateFlow<Resource<UserEntity>> get() = _userDetailState

    fun getUserDetail(userId: Int) {
        viewModelScope.launch {
            getUserDetailUseCase.execute(userId).collect { userDetailResource ->
                _userDetailState.value = userDetailResource
                d("UserDetailViewModel", "User detail resource: $userDetailResource")
            }
        }
    }
}

