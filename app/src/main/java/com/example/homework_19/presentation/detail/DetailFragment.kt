package com.example.homework_19.presentation.detail

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.homework_19.data.common.Resource
import com.example.homework_19.databinding.FragmentDetailBinding
import com.example.homework_19.domain.model.UserEntity
import com.example.homework_19.presentation.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel: DetailViewModel by viewModels()
    override fun setUp() {
        extractUserId()
    }

    override fun bindObservers() {
        observeUserDetail()
    }

    private fun extractUserId() {
        val userId = arguments?.getInt("userId") ?: -1
        if (userId != -1) {
            viewModel.getUserDetail(userId)
        } else {
            showError("User id is not valid")
        }
    }

    private fun observeUserDetail() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userDetailState.collect { userDetailResource ->
                    when (userDetailResource) {
                        is Resource.Success -> showUserDetail(userDetailResource.data)
                        is Resource.Error -> showError(userDetailResource.errorMessage)
                        is Resource.Loading -> showLoading(userDetailResource.loading)
                    }
                }
            }
        }
    }

    private fun showUserDetail(userDetail: UserEntity) {
        binding.apply {
            tvId.text = "User ID: ${userDetail.id}"
            tvFullName.text = "${userDetail.firstName} ${userDetail.lastName}"
            tvEmail.text = userDetail.email
            Glide.with(binding.avatar.context)
                .load(userDetail.avatar)
                .into(binding.avatar)
        }
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(loading: Boolean) {
        binding.progressBar.isVisible = loading
    }
}