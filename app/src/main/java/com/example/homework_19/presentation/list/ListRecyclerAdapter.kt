package com.example.homework_19.presentation.list

import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_19.databinding.ListItemBinding
import com.example.homework_19.presentation.model.User

class ListRecyclerAdapter(private val onItemClick: (Int) -> Unit) :
    ListAdapter<User, ListRecyclerAdapter.UserListViewHolder>(UserListDiffCallback()) {

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

        fun bind(user: User) {
            binding.apply {
                tvFullName.text = user.fullName
                tvEmail.text = user.email
                Glide.with(binding.avatar.context)
                    .load(user.avatar)
                    .into(binding.avatar)
            }
            itemView.setOnClickListener {
                d("UserListAdapter", "Item clicked. User ID: ${user.id}")
                onItemClick.invoke(user.id)
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
