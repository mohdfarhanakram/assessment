package com.justclean.android.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.justclean.android.databinding.FragmentPostDetailBinding
import com.justclean.android.domain.Comment
import com.justclean.android.presentation.BaseFragment
import com.justclean.android.presentation.Constant
import com.justclean.android.presentation.adapter.CommentAdapter
import com.justclean.android.presentation.vm.CommentListViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 *   Created by Mohd Farhan on 15/10/2020.
 */
@AndroidEntryPoint
class PostDetailFragment : BaseFragment() , View.OnClickListener{

    private val viewModel: CommentListViewModel by viewModels()
    lateinit var binding : FragmentPostDetailBinding
    var postId : String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        postId = arguments?.getString(Constant.POST_ID)
        loaderView = binding.loading
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveData.observe(this.viewLifecycleOwner, Observer { showResult(it) })
        postId?.let { viewModel.fetchCommentList(it) }
    }

    override fun populateUi(result: Any) {
        if (result is List<*>) { // Show List
            binding.commentRV.adapter = CommentAdapter(requireContext(), result as List<Comment>,this);
        }

    }

    override fun onClick(p0: View?) {
    }

}
