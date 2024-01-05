package com.example.homework_19.presentation.list

import android.util.Log.d
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_19.data.common.Resource
import com.example.homework_19.databinding.FragmentListBinding
import com.example.homework_19.domain.model.UserList
import com.example.homework_19.presentation.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate),
    OnItemClickListener {

    private val viewModel: ListViewModel by viewModels()
    private lateinit var listRecyclerAdapter: ListRecyclerAdapter

    override fun setUp() {
        setupRecyclerView()
    }

    override fun onClickListeners() {
    }

    override fun bindObservers() {
        observeViewModel()
    }

    override fun onItemClick(user: UserList) {
        // Handle item click here, e.g., navigate to details fragment
//        viewModel.navigateToUserDetail(user.id)
    }

    private fun setupRecyclerView() {
        listRecyclerAdapter = ListRecyclerAdapter(this)
        binding.listRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listRecyclerAdapter
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userList.collect {
                    d("ListFragment", "userList observed: $it")
                    when (it) {
                        is Resource.Loading -> {
                        }

                        is Resource.Success -> {
                            listRecyclerAdapter.submitList(it.data)
                        }

                        is Resource.Error -> {
                        }
                    }
                }
            }
        }
    }
}
