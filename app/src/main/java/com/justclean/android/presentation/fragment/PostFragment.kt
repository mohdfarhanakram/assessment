package com.justclean.android.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.justclean.android.R
import com.justclean.android.databinding.FragmentPostLayoutBinding
import com.justclean.android.domain.Post
import com.justclean.android.presentation.BaseFragment
import com.justclean.android.presentation.Constant
import com.justclean.android.presentation.activity.PostDetailActivity
import com.justclean.android.presentation.adapter.PostAdapter
import com.justclean.android.presentation.vm.AddFavViewModel
import com.justclean.android.presentation.vm.PostListViewModel
import dagger.hilt.android.AndroidEntryPoint

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
         loaderView = binding.loading;

         return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.responseLiveData().observe(this.viewLifecycleOwner, Observer { showResult(it) })
        viewModelAddFav.responseLiveData().observe(this.viewLifecycleOwner, Observer { showResult(it) })

        viewModel.fetchPostList()
    }


    override fun populateUi(result: Any) {
        if (result is List<*>) { // Show List
            binding.postRv.adapter = PostAdapter(requireContext(), result as List<Post>,this);
        }else if(result is String){ // Add to Fav
           showSnackBar(result as String)
        }

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