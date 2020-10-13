package com.justclean.android.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.justclean.android.databinding.RowPostLayoutBinding
import com.justclean.android.domain.Post

/**
 *   Created by Mohd Farhan on 13/10/2020.
 */

class PostAdapter(
    val context: Context,
    val postList: List<Post>,
    val listener: View.OnClickListener
) : RecyclerView.Adapter<PostAdapter.PostVH>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RowPostLayoutBinding = RowPostLayoutBinding.inflate(layoutInflater, parent, false)
        binding.listener = listener
        return PostVH(binding)
    }

    override fun onBindViewHolder(holder: PostVH, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int = postList.size


    class PostVH(binding: RowPostLayoutBinding) :
        RecyclerView.ViewHolder(binding.getRoot()) {
        private val binding: RowPostLayoutBinding
        fun bind(post: Post) {
            binding.post = post
            binding.executePendingBindings()
        }
        init {
            this.binding = binding
        }
    }

}