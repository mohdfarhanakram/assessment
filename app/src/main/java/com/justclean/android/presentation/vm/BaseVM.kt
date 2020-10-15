package com.justclean.android.presentation.vm

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.justclean.android.domain.Result
import io.reactivex.disposables.CompositeDisposable

/**
 *   Created by Mohd Farhan on 15/10/2020.
 */
open class BaseVM : ViewModel() , LifecycleObserver {

    val compositeDisposable = CompositeDisposable()
    val liveData = MutableLiveData<Result<Any>>()

    fun responseLiveData() : MutableLiveData<Result<Any>>{
        return liveData
    }


    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}