package com.example.homework_19.presentation.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_19.data.common.Resource
import com.example.homework_19.domain.usecase.GetUserDetailUseCase
import com.example.homework_19.presentation.event.detail.UserDetailEvent
import com.example.homework_19.presentation.mapper.toPresentation
import com.example.homework_19.presentation.state.detail.UserDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase
) : ViewModel() {

    private val _userDetailState = MutableStateFlow(UserDetailState())
    val userDetailState: SharedFlow<UserDetailState> get() = _userDetailState

    fun onEvent(event: UserDetailEvent) {
        when (event) {
            is UserDetailEvent.GetUserDetail -> getUserDetail(event.userId)
        }
    }
    private fun getUserDetail(userId: Int) {
        viewModelScope.launch {
            getUserDetailUseCase.invoke(userId).collect {
                when(it) {
                    is Resource.Success -> {
                        _userDetailState.update {
                            currentState -> currentState.copy(
                            details = it.data.toPresentation()
                            )
                        }
                    }
                    is Resource.Error -> {
                        _userDetailState.update {
                            currentState -> currentState.copy(
                            errorMessage = it.errorMessage
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _userDetailState.update {
                            currentState -> currentState.copy(
                            isLoading = it.loading
                            )
                        }
                    }
                }
            }
        }
    }
}



