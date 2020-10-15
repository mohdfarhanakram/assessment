package com.justclean.android.presentation.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.justclean.android.data.repository.Repository
import com.justclean.android.domain.Post
import com.justclean.android.domain.Response
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 *   Created by Mohd Farhan on 13/10/2020.
 */

class PostListViewModel @ViewModelInject constructor(
    private val repository: Repository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() , LifecycleObserver {

    private val compositeDisposable = CompositeDisposable()
    private val responseLiveData = MutableLiveData<Response<List<Post>>>()
    private val response = Response<List<Post>>()



    fun fetchPostList() {
        compositeDisposable.add(repository.getPostList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { responseLiveData.postValue(response.loading()) }
            .subscribe({ postList ->responseLiveData.postValue(response.setData(postList))
               }, { error ->
                responseLiveData.postValue(response.setError(error))
            })
        )
    }


    fun getPostLiveData(): MutableLiveData<Response<List<Post>>> {
        return responseLiveData
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }


}