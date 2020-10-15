package com.justclean.android.presentation.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.justclean.android.data.repository.Repository
import com.justclean.android.domain.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *   Created by Mohd Farhan on 13/10/2020.
 */

class PostListViewModel @ViewModelInject constructor(
    private val repository: Repository,
    @Assisted private val savedStateHandle: SavedStateHandle
): BaseVM() {

    fun fetchPostList() {
        compositeDisposable.add(repository.getPostList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { liveData.postValue(Result.Loading) }
            .subscribe({ postList ->liveData.postValue(Result.Success(postList))
               }) { error ->
                liveData.postValue(error.message?.let { Result.Error(it) })
            }
        )
    }

}