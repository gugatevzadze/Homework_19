package com.example.homework_19.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_19.data.common.Resource
import com.example.homework_19.domain.model.UserEntity
import com.example.homework_19.domain.usecase.GetUserListUseCase
import com.example.homework_19.presentation.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val getUserListUseCase: GetUserListUseCase) :
    ViewModel() {

    private val _userListState = MutableStateFlow<Resource<List<User>>>(Resource.Loading(false))
    val userListState: StateFlow<Resource<List<User>>> get() = _userListState

    private val _navigationEvent = MutableStateFlow<UserListNavigation?>(null)
    val navigationEvent: StateFlow<UserListNavigation?> get() = _navigationEvent

    fun fetchUserList() {
        viewModelScope.launch {
            getUserListUseCase.execute().collect { userListResource ->
                _userListState.value = when (userListResource) {
                    is Resource.Success -> Resource.Success(mapToPresentation(userListResource.data))
                    is Resource.Error -> userListResource
                    is Resource.Loading -> userListResource
                }
            }
        }
    }

    private fun mapToPresentation(userList: List<UserEntity>): List<User> {
        return userList.map { userEntity ->
            User(
                id = userEntity.id,
                fullName = "${userEntity.firstName} ${userEntity.lastName}",
                email = userEntity.email,
                avatar = userEntity.avatar
            )
        }
    }

    fun onUserItemClick(userId: Int) {
        _navigationEvent.value = UserListNavigation.NavigateToDetail(userId)
    }

    fun onNavigationHandled() {
        _navigationEvent.value = null
    }
}
sealed class UserListNavigation {
    data class NavigateToDetail(val userId: Int) : UserListNavigation()
}




