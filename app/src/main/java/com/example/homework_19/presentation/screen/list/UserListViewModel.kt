package com.example.homework_19.presentation.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_19.data.common.Resource
import com.example.homework_19.domain.usecase.DeleteUserUseCase
import com.example.homework_19.domain.usecase.GetUserListUseCase
import com.example.homework_19.presentation.event.list.UserListEvent
import com.example.homework_19.presentation.mapper.toPresentation
import com.example.homework_19.presentation.model.User
import com.example.homework_19.presentation.state.list.UserListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
    private val deleteUserListUseCase: DeleteUserUseCase
) :
    ViewModel() {

    private val _userUserListState = MutableStateFlow(UserListState())
    val userListState: SharedFlow<UserListState> get() = _userUserListState

    private val _navigationEvent = MutableSharedFlow<UserListNavigation>()
    val navigationEvent: SharedFlow<UserListNavigation?> get() = _navigationEvent

    fun onEvent(event: UserListEvent) {
        when (event) {
            is UserListEvent.GetUserList -> getUserList()
            is UserListEvent.UserItemClick -> onUserItemClick(event.user)
            is UserListEvent.UserItemSelect -> onUserItemSelect(event.user)
            is UserListEvent.UserItemDeselect -> onUserItemDeselect(event.user)
            is UserListEvent.UserItemDelete -> onUserItemDelete()
//            is UserListEvent.UserItemDelete -> deleteUser(event.userId)
        }
    }

    private fun getUserList() {
        viewModelScope.launch {
            getUserListUseCase.invoke().collect { it ->
                when (it) {
                    is Resource.Success -> {
                        _userUserListState.update { currentState ->
                            currentState.copy(
                                users = it.data.map { it.toPresentation() }
                            )
                        }
                    }

                    is Resource.Error -> {
                        _userUserListState.update { currentState ->
                            currentState.copy(
                                errorMessage = it.errorMessage
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _userUserListState.update { currentState ->
                            currentState.copy(
                                isLoading = it.loading
                            )
                        }
                    }
                }
            }
        }
    }

    private fun deleteUser(userId: Int) {
        viewModelScope.launch {
            deleteUserListUseCase.invoke(userId)
        }
    }

    private fun onUserItemClick(user: User) {
        viewModelScope.launch {
            _navigationEvent.emit(UserListNavigation.NavigateToDetail(user.id))
        }
    }

    private fun onUserItemSelect(user: User) {
        viewModelScope.launch {
            _userUserListState.update { currentState ->
                currentState.copy(
                    users = currentState.users?.map {
                        if (it.id == user.id) {
                            it.copy(isSelected = true)
                        } else {
                            it
                        }
                    }
                )
            }
        }
    }

    private fun onUserItemDeselect(user: User) {
        viewModelScope.launch {
            _userUserListState.update { currentState ->
                currentState.copy(
                    users = currentState.users?.map {
                        if (it.id == user.id) {
                            it.copy(isSelected = false)
                        } else {
                            it
                        }
                    }
                )
            }
        }
    }

    private fun onUserItemDelete() {
        viewModelScope.launch {
            _userUserListState.update { currentState ->
                currentState.copy(
                    users = currentState.users?.filter { !it.isSelected }
                )
            }
        }
    }

    sealed interface UserListNavigation {
        data class NavigateToDetail(val userId: Int) : UserListNavigation
    }
}