package com.justclean.android.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.justclean.android.data.repository.Repository
import com.justclean.android.presentation.vm.AddFavViewModel
import com.justclean.android.presentation.vm.CommentListViewModel
import com.justclean.android.presentation.vm.FavPostListViewModel
import com.justclean.android.presentation.vm.PostListViewModel
import javax.inject.Inject

/**
 *   Created by Mohd Farhan on 13/10/2020.
 */
class ViewModelFactory @Inject constructor(private val repository: Repository) : ViewModelProvider.Factory{


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        /*if (modelClass.isAssignableFrom(PostListViewModel::class.java)) {
            return PostListViewModel(repository) as T
        }*/

       /* if (modelClass.isAssignableFrom(CommentListViewModel::class.java)) {
            return CommentListViewModel(repository) as T
        }*/

        /*if (modelClass.isAssignableFrom(AddFavViewModel::class.java)) {
            return AddFavViewModel(repository) as T
        }*/

       /* if (modelClass.isAssignableFrom(FavPostListViewModel::class.java)) {
            return FavPostListViewModel(repository) as T
        }*/
        throw IllegalArgumentException("Unknown class name in ViewModelFactory")
    }

}