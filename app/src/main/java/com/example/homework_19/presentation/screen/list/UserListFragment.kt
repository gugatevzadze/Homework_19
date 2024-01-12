package com.example.homework_19.presentation.screen.list

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_19.databinding.FragmentListBinding
import com.example.homework_19.presentation.common.BaseFragment
import com.example.homework_19.presentation.event.list.UserListEvent
import com.example.homework_19.presentation.model.User
import com.example.homework_19.presentation.state.list.UserListState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private val viewModel: UserListViewModel by viewModels()
    private lateinit var userListAdapter: UserListRecyclerAdapter

    override fun setUp() {
        initRecyclerView()
        handleDeleteButton()
    }
    override fun bindObservers() {
        observeUserList()
        observeNavigationEvents()
    }

    private fun observeUserList() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userListState.collect {
                    handleListState(state = it)
                }
            }
        }
    }

    private fun observeNavigationEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.navigationEvent.collect { navigationEvent ->
                    handleNavigationEvent(navigationEvent)
                }
            }
        }
    }

    private fun initRecyclerView() {
        userListAdapter = UserListRecyclerAdapter (
            onItemClick = {
                handleUserItemClick(it)
            },
            onItemSelect = {
                handleUserItemSelection(it)
            }
        )
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = userListAdapter
        }
        viewModel.onEvent(UserListEvent.GetUserList)
    }

    private fun handleUserItemClick(user: User) {
        viewModel.onEvent(UserListEvent.UserItemClick(user = user))
    }

    private fun handleUserItemSelection(user: User) {
        if (user.isSelected) {
            viewModel.onEvent(UserListEvent.UserItemSelect(user = user))
        } else {
            viewModel.onEvent(UserListEvent.UserItemDeselect(user = user))
        }
        binding.btnDelete.isEnabled = userListAdapter.currentList.any { it.isSelected }
    }

    private fun handleDeleteButton() {
        binding.btnDelete.setOnClickListener {
            viewModel.onEvent(UserListEvent.UserItemDelete)
        }
    }
    private fun handleListState(state: UserListState) {
        state.users?.let {
            userListAdapter.submitList(it)
        }
        state.errorMessage?.let {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        binding.progressBar.isVisible = state.isLoading
    }

    private fun handleNavigationEvent(navigationEvent: UserListViewModel.UserListNavigation?) {
        when (navigationEvent) {
            is UserListViewModel.UserListNavigation.NavigateToDetail -> navigateToUserDetail(
                navigationEvent.userId
            )
            else ->{}
        }
    }

    private fun navigateToUserDetail(userId: Int) {
        val action = UserListFragmentDirections.actionListFragmentToDetailFragment(userId)
        findNavController().navigate(action)
    }
}




