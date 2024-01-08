package com.example.homework_19.presentation.list

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_19.data.common.Resource
import com.example.homework_19.databinding.FragmentListBinding
import com.example.homework_19.presentation.common.BaseFragment
import com.example.homework_19.presentation.model.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private val viewModel: UserListViewModel by viewModels()
    private lateinit var userListAdapter: ListRecyclerAdapter

    override fun setUp() {
        initRecyclerView()
        viewModel.fetchUserList()
        setListeners()
    }

    private fun initRecyclerView() {
        userListAdapter = ListRecyclerAdapter(
            onItemClick = { userId -> viewModel.onUserItemClick(userId) },
            onItemLongClick = { position -> userListAdapter.toggleItemSelection(position) }
        )
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userListAdapter
        }
    }

    private fun setListeners() {
        binding.btnDelete.setOnClickListener {
            viewModel.deleteSelectedItems()
        }
    }

    override fun bindObservers() {
        observeUserList()
        observeNavigationEvents()
    }

    private fun observeUserList() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userListState.collect { userListResource ->
                    when (userListResource) {
                        is Resource.Success -> showUserList(userListResource.data)
                        is Resource.Error -> showError(userListResource.errorMessage)
                        is Resource.Loading -> showLoading(userListResource.loading)
                    }
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

    private fun showUserList(userList: List<User>) {
        userListAdapter.submitList(userList)
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(loading: Boolean) {
        binding.progressBar.isVisible = loading
    }

    private fun handleNavigationEvent(navigationEvent: UserListNavigation?) {
        when (navigationEvent) {
            is UserListNavigation.NavigateToDetail -> navigateToUserDetail(navigationEvent.userId)
            else -> {
            }
        }
        viewModel.onNavigationHandled()
    }

    private fun navigateToUserDetail(userId: Int) {
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(userId)
        findNavController().navigate(action)
    }
}




