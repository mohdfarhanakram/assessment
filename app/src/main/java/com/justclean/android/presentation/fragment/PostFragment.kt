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
import com.justclean.android.databinding.FragmentPostLayoutBinding
import com.justclean.android.di.ViewModelFactory
import com.justclean.android.domain.Post
import com.justclean.android.domain.Response
import com.justclean.android.presentation.BaseFragment
import com.justclean.android.presentation.Constant
import com.justclean.android.presentation.activity.PostDetailActivity
import com.justclean.android.presentation.adapter.PostAdapter
import com.justclean.android.presentation.vm.AddFavViewModel
import com.justclean.android.presentation.vm.PostListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 *   Created by Mohd Farhan on 12/10/2020.
 */

@AndroidEntryPoint
class PostFragment : BaseFragment() , View.OnClickListener{

    private val viewModel: PostListViewModel by viewModels()
    private val viewModelAddFav: AddFavViewModel by viewModels()

    private lateinit var adapter: PostAdapter
    lateinit var binding : FragmentPostLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         super.onCreateView(inflater, container, savedInstanceState)
         binding = FragmentPostLayoutBinding.inflate(inflater,container,false)
         return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPostLiveData().observe(this.viewLifecycleOwner, Observer { populateUI(it) })
        viewModelAddFav.getFavLiveData().observe(this.viewLifecycleOwner, Observer { showAddToFavResult(it) })

        viewModel.fetchPostList()
    }

    private fun populateUI(result: Response<List<Post>>) {
        when (result.status) {
            Response.Status.ERROR -> {
                loader(false)
            }

            Response.Status.LOADING -> {
                loader(true)
            }

            Response.Status.SUCCESS -> {
                loader(false)
                binding.postRv.adapter = result.data?.let { PostAdapter(requireContext(), it,this) };
            }
        }
    }

    private fun showAddToFavResult(result: Response<String>) {
        when (result.status) {
            Response.Status.ERROR -> {
                loader(false)
            }

            Response.Status.LOADING -> {
                loader(true)
            }

            Response.Status.SUCCESS -> {
                result.data?.let { showSnackBar(it) }
                loader(false)
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
        when(view.id){
          R.id.postLayout->{
              val intent = Intent(requireContext(), PostDetailActivity::class.java)
              intent.putExtra(Constant.POST_ID,(view.tag as Post).id)
              startActivity(intent)
          }

          R.id.favIv->{
             viewModelAddFav.addTiFavList(view.tag as Post)
          }
        }
    }
}