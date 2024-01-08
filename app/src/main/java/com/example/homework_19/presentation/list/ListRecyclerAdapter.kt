package com.example.homework_19.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_19.R
import com.example.homework_19.databinding.ListItemBinding
import com.example.homework_19.presentation.model.User

class ListRecyclerAdapter(
    private val onItemClick: (Int) -> Unit,
    private val onItemLongClick: (Int) -> Unit
) :
    ListAdapter<User, ListRecyclerAdapter.UserListViewHolder>(UserListDiffCallback()) {

    // Updated list to hold selected items
    private val selectedItems = mutableListOf<User>()

    fun getSelectedItems(): List<User> {
        return selectedItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
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

                // Set background color based on selection state
                itemView.setBackgroundResource(if (user.isSelected) R.color.selectedColor else android.R.color.transparent)
            }

            itemView.setOnClickListener {
                onItemClick.invoke(user.id)
            }

            itemView.setOnLongClickListener {
                user.isSelected = !user.isSelected
                notifyItemChanged(adapterPosition)
                true
            }
        }
    }

    // Function to toggle item selection and update the list
    fun toggleItemSelection(position: Int) {
        val item = getItem(position)
        item.isSelected = !item.isSelected

        if (item.isSelected) {
            selectedItems.add(item)
        } else {
            selectedItems.remove(item)
        }
    }

    // Function to delete selected items
    fun deleteSelectedItems() {
        val selectedIds = selectedItems.map { it.id }
        val newList = currentList.filterNot { selectedIds.contains(it.id) }
        submitList(newList)
        selectedItems.clear()
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


