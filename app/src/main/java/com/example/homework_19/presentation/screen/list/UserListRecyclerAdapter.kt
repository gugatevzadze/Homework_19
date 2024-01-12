package com.example.homework_19.presentation.screen.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_19.databinding.ListItemBinding
import com.example.homework_19.presentation.model.User

class UserListRecyclerAdapter(
    private val onItemClick: (User) -> Unit,
    private val onItemSelect: (User) -> Unit
) :
    ListAdapter<User, UserListRecyclerAdapter.UserListViewHolder>(UserListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind()
    }

    inner class UserListViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var user: User
        fun bind() {
            user = currentList[adapterPosition]
            binding.apply {
                tvFullName.text = user.fullName
                tvEmail.text = user.email
                checkbox.isChecked = user.isSelected
                Glide.with(binding.avatar.context)
                    .load(user.avatar)
                    .into(binding.avatar)
            }

            itemView.setOnClickListener {
                onItemClick.invoke(user)
            }

            binding.checkbox.setOnCheckedChangeListener{ _, isChecked ->
                user.isSelected = isChecked
                onItemSelect.invoke(user)
            }
        }
    }
    private class UserListDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}