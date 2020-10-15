package com.justclean.android.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
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
import com.justclean.android.presentation.fragment.PostDetailFragment
import com.justclean.android.presentation.vm.CommentListViewModel
import com.justclean.android.presentation.vm.PostListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 *   Created by Mohd Farhan on 13/10/2020.
 */
@AndroidEntryPoint
class PostDetailActivity : BaseActivity(), View.OnClickListener{

    lateinit var binding : ActivityPostDetailBinding
     var postId : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_detail)
        binding.listener = this
        postId = intent.getStringExtra(Constant.POST_ID)

        if(savedInstanceState==null){
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            val fragment = PostDetailFragment()
            val bundle = Bundle()
            bundle.putString(Constant.POST_ID,postId)
            fragment.arguments = bundle
            fragmentTransaction.add(R.id.commentFragmentContainer, fragment)
            fragmentTransaction.commit()
        }

    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.backIv->{
                finish()
            }
        }
    }
}