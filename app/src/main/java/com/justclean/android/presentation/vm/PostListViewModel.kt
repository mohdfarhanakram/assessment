package com.justclean.android.presentation.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.justclean.android.data.repository.Repository
import com.justclean.android.domain.Post
import com.justclean.android.domain.Response
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 *   Created by Mohd Farhan on 13/10/2020.
 */

class PostListViewModel @Inject constructor(private val repository: Repository): ViewModel(){

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

    fun fetchPostListFromDb() {
        compositeDisposable.add(repository.getPostListFromDb()
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