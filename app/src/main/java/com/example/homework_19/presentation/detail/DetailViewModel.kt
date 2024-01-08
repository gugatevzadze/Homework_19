package com.example.homework_19.presentation.detail

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_19.data.common.Resource
import com.example.homework_19.domain.model.UserEntity
import com.example.homework_19.domain.usecase.GetUserDetailUseCase
import com.example.homework_19.presentation.mapper.toPresentation
import com.example.homework_19.presentation.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase
) : ViewModel() {

    private val _userDetailState = MutableStateFlow<Resource<User>>(Resource.Loading(false))
    val userDetailState: StateFlow<Resource<User>> get() = _userDetailState

    fun getUserDetail(userId: Int) {
        viewModelScope.launch {
            getUserDetailUseCase.execute(userId).collect { userDetailResource ->
                _userDetailState.value = userDetailResource.mapToPresentation()
                d("UserDetailViewModel", "User detail resource: $userDetailResource")
            }
        }
    }

    private fun Resource<UserEntity>.mapToPresentation(): Resource<User> {
        return when (this) {
            is Resource.Success -> Resource.Success(data.toPresentation())
            is Resource.Error -> Resource.Error(errorMessage)
            is Resource.Loading -> Resource.Loading(loading)
        }
    }
}



