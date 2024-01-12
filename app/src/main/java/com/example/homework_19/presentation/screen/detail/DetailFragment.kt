package com.example.homework_19.presentation.screen.detail

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.homework_19.databinding.FragmentDetailBinding
import com.example.homework_19.presentation.common.BaseFragment
import com.example.homework_19.presentation.event.detail.UserDetailEvent
import com.example.homework_19.presentation.state.detail.UserDetailState
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

    private fun observeUserDetail() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userDetailState.collect {
                    hanleUserDetail(state = it)
                }
            }
        }
    }

    private fun hanleUserDetail(state: UserDetailState) {
        state.details?.let{
            binding.apply {
                tvFullName.text = it.fullName
                tvEmail.text = it.email
                Glide.with(binding.avatar.context)
                    .load(it.avatar)
                    .into(binding.avatar)
            }
        }
        state.errorMessage?.let {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        binding.progressBar.isVisible = state.isLoading
    }
    private fun extractUserId() {
        val userId = arguments?.getInt("userId") ?: -1
        viewModel.onEvent(UserDetailEvent.GetUserDetail(userId = userId))
    }
}