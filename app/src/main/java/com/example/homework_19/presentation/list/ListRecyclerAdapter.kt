package com.example.homework_19.presentation.list

import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_19.databinding.ListItemBinding
import com.example.homework_19.domain.model.UserList

class ListRecyclerAdapter(private val itemClickListener: OnItemClickListener) :
    ListAdapter<UserList, ListRecyclerAdapter.UserListViewHolder>(UserListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
        d("ListRecyclerAdapter", "Bind item: $currentItem")
    }

    inner class UserListViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val user = getItem(position)
                    itemClickListener.onItemClick(user)
                }
            }
        }

        fun bind(user: UserList) {
            binding.apply {
                tvFirstName.text = user.firstName
                tvLastName.text = user.lastName
                tvEmail.text = user.email
                Glide.with(binding.avatar.context)
                    .load(user.avatar)
                    .into(binding.avatar)
            }
        }
    }

    private class UserListDiffCallback : DiffUtil.ItemCallback<UserList>() {
        override fun areItemsTheSame(oldItem: UserList, newItem: UserList): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserList, newItem: UserList): Boolean {
            return oldItem == newItem
        }
    }
}
