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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 *   Created by Mohd Farhan on 13/10/2020.
 */

class AddFavViewModel @ViewModelInject constructor(
    private val repository: Repository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() , LifecycleObserver {

    private val compositeDisposable = CompositeDisposable()
    private val responseLiveData = MutableLiveData<Response<String>>()
    private val response = Response<String>()



    fun addTiFavList(post: Post) {
         compositeDisposable.add(repository.addFav(post)
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .doOnSubscribe { responseLiveData.postValue(response.loading()) }
             .subscribe({ responseLiveData.postValue(response.setData("Successfully Added to Fav list"))  }, { error ->
                 responseLiveData.postValue(response.setError(error))
             })
         )
     }

    fun getFavLiveData(): MutableLiveData<Response<String>> {
        return responseLiveData
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }


}