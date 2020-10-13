package com.justclean.android.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.justclean.android.databinding.RowFavPostLayoutBinding
import com.justclean.android.domain.Fav

/**
 *   Created by Mohd Farhan on 13/10/2020.
 */
class FavPostAdapter (
    val context: Context,
    val postList: List<Fav>,
    val listener: View.OnClickListener
) : RecyclerView.Adapter<FavPostAdapter.FavPostVH>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavPostVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RowFavPostLayoutBinding = RowFavPostLayoutBinding.inflate(layoutInflater, parent, false)
        binding.listener = listener
        return FavPostVH(binding)
    }

    override fun onBindViewHolder(holder: FavPostVH, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int = postList.size


    class FavPostVH(binding: RowFavPostLayoutBinding) :
        RecyclerView.ViewHolder(binding.getRoot()) {
        private val binding: RowFavPostLayoutBinding
        fun bind(fav: Fav) {
            binding.fav = fav
            binding.executePendingBindings()
        }
        init {
            this.binding = binding
        }
    }

}