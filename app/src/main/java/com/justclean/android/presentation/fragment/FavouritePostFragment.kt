package com.justclean.android.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.justclean.android.databinding.FragmentFavPostLayoutBinding
import com.justclean.android.domain.Fav
import com.justclean.android.presentation.BaseFragment
import com.justclean.android.presentation.adapter.FavPostAdapter
import com.justclean.android.presentation.vm.FavPostListViewModel
import dagger.hilt.android.AndroidEntryPoint

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

        viewModel.responseLiveData().observe(this.viewLifecycleOwner, Observer { showResult(it) })
        viewModel.fetchFavPostList()
    }

    override fun populateUi(result: Any) {
        if (result is List<*>) { // Show List
            binding.favPostRv.adapter = FavPostAdapter(requireContext(), result as List<Fav>,this);
        }
    }

    override fun onClick(view: View) {

    }

}