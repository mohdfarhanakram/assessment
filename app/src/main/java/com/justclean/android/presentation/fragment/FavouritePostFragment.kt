package com.justclean.android.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.justclean.android.R
import com.justclean.android.databinding.FragmentFavPostLayoutBinding
import com.justclean.android.di.ViewModelFactory
import com.justclean.android.domain.Fav
import com.justclean.android.domain.Post
import com.justclean.android.domain.Response
import com.justclean.android.presentation.BaseFragment
import com.justclean.android.presentation.Constant
import com.justclean.android.presentation.activity.PostDetailActivity
import com.justclean.android.presentation.adapter.FavPostAdapter
import com.justclean.android.presentation.vm.FavPostListViewModel
import com.justclean.android.presentation.vm.PostListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 *   Created by Mohd Farhan on 12/10/2020.
 */

@AndroidEntryPoint
class FavouritePostFragment : BaseFragment() , View.OnClickListener{

    private val viewModel: FavPostListViewModel by viewModels()
    private lateinit var adapter: FavPostAdapter
    private lateinit var binding : FragmentFavPostLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentFavPostLayoutBinding.inflate(inflater,container,false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFavPostLiveData().observe(this.viewLifecycleOwner, Observer { populateUI(it) })
        viewModel.fetchFavPostList()

    }

    private fun populateUI(result: Response<List<Fav>>) {
        when (result.status) {
            Response.Status.ERROR -> {
                loader(false)
            }

            Response.Status.LOADING -> {
                loader(true)
            }

            Response.Status.SUCCESS -> {
                loader(false)
                binding.favPostRv.adapter =
                    result.data?.let { FavPostAdapter(requireContext(), it,this) }
            }
        }
    }

    private fun loader(show: Boolean){
        if(show)
            binding.loading.visibility = View.VISIBLE
        else
            binding.loading.visibility = View.GONE
    }

    override fun onClick(view: View) {

    }

}