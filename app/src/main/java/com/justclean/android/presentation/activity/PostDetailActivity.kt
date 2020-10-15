package com.justclean.android.presentation.activity

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.justclean.android.R
import com.justclean.android.databinding.ActivityPostDetailBinding
import com.justclean.android.presentation.BaseActivity
import com.justclean.android.presentation.Constant
import com.justclean.android.presentation.fragment.PostDetailFragment
import dagger.hilt.android.AndroidEntryPoint

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