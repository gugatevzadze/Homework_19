package com.example.homework_19.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_19.data.common.Resource
import com.example.homework_19.domain.usecase.GetUserListUseCase
import com.example.homework_19.presentation.mapper.toPresentation
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
                    is Resource.Success -> Resource.Success(userListResource.data.map { it.toPresentation() })
                    is Resource.Error -> userListResource
                    is Resource.Loading -> userListResource
                }
            }
        }
    }

    fun onUserItemClick(userId: Int) {
        _navigationEvent.value = UserListNavigation.NavigateToDetail(userId)
    }

    fun onNavigationHandled() {
        _navigationEvent.value = null
    }

    // Function to delete selected items
    fun deleteSelectedItems() {
        viewModelScope.launch {
            val currentState = userListState.value
            if (currentState is Resource.Success) {
                val selectedItems = currentState.data.filter { it.isSelected }
                if (selectedItems.isNotEmpty()) {
                    // Perform local deletion logic
                    val updatedList = currentState.data.filterNot { it.isSelected }.toMutableList()

                    // If you want to perform any additional logic, such as updating UI or saving to a local database, do it here

                    // Notify the UI about the changes
                    _userListState.value = Resource.Success(updatedList)

                    // If you want to refresh the list after deletion, you can call fetchUserList()
                    // However, keep in mind that this depends on how your data source works
//                     fetchUserList()
                }
            }
        }
    }

}

sealed class UserListNavigation {
    data class NavigateToDetail(val userId: Int) : UserListNavigation()
}




