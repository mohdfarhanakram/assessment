package com.justclean.android.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.justclean.android.databinding.RowCommentLayoutBinding
import com.justclean.android.domain.Comment

/**
 *   Created by Mohd Farhan on 13/10/2020.
 */

class CommentAdapter(
    val context: Context,
    val commentList: List<Comment>,
    val listener: View.OnClickListener
) : RecyclerView.Adapter<CommentAdapter.CommentVH>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RowCommentLayoutBinding = RowCommentLayoutBinding.inflate(layoutInflater, parent, false)
        binding.listener = listener
        return CommentVH(binding)
    }

    override fun onBindViewHolder(holder: CommentVH, position: Int) {
        holder.bind(commentList[position])
    }

    override fun getItemCount(): Int = commentList.size


    class CommentVH(binding: RowCommentLayoutBinding) :
        RecyclerView.ViewHolder(binding.getRoot()) {
        private val binding: RowCommentLayoutBinding
        fun bind(comment: Comment) {
            binding.comment = comment
            binding.executePendingBindings()
        }
        init {
            this.binding = binding
        }
    }

}