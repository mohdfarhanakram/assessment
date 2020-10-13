package com.justclean.android.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.justclean.android.R
import com.justclean.android.databinding.ActivityPostDetailBinding
import com.justclean.android.di.ViewModelFactory
import com.justclean.android.domain.Comment
import com.justclean.android.domain.Post
import com.justclean.android.domain.Response
import com.justclean.android.presentation.BaseActivity
import com.justclean.android.presentation.Constant
import com.justclean.android.presentation.adapter.CommentAdapter
import com.justclean.android.presentation.vm.CommentListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 *   Created by Mohd Farhan on 13/10/2020.
 */
@AndroidEntryPoint
class PostDetailActivity : BaseActivity(), View.OnClickListener{

    @Inject
    lateinit var factory: ViewModelFactory
    private lateinit var viewModel: CommentListViewModel
    lateinit var binding : ActivityPostDetailBinding

     var postId : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_detail)
        binding.listener = this
        postId = intent.getStringExtra(Constant.POST_ID)

    }

    override fun onResume() {
        super.onResume()

        viewModel = ViewModelProvider(this,factory)[CommentListViewModel::class.java]
        viewModel.getCommentLiveData().observe(this, Observer { populateUI(it) })

        postId?.let { viewModel.fetchCommentList(it) }
    }

    private fun populateUI(result: Response<List<Comment>>) {
        when (result.status) {
            Response.Status.ERROR -> {
                loader(false)
            }

            Response.Status.LOADING -> {
                loader(true)
            }

            Response.Status.SUCCESS -> {
                loader(false)
                binding.commentRV.adapter = result.data?.let { CommentAdapter(this, it,this) }
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
            R.id.backIv->{
                finish()
            }
        }
    }
}