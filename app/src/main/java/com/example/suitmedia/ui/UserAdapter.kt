package com.example.suitmedia.ui

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.suitmedia.data.response.UsersItem
import com.example.suitmedia.databinding.UserItemLayoutBinding

class UserAdapter(private val activity: Activity) : ListAdapter<UsersItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = UserItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)

        holder.itemView.setOnClickListener {
            val user = getItem(position)
            val intent = Intent(holder.itemView.context, SecondScreenActivity::class.java)
            val fullName = "${user.firstName} ${user.lastName}"
            intent.putExtra("USERNAME", fullName)
            activity.setResult(Activity.RESULT_OK, intent)

            activity.finish()

        }
    }

    class MyViewHolder(val binding: UserItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UsersItem){
            binding.tvFirstName.text = user.firstName
            binding.tvLastName.text = user.lastName
            binding.tvEmail.text = user.email
            Glide.with(binding.imgItemPhoto).load(user.avatar).transform(CircleCrop()).into(binding.imgItemPhoto)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UsersItem>() {
            override fun areItemsTheSame(oldItem: UsersItem, newItem: UsersItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UsersItem, newItem: UsersItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}