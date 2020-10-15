package com.justclean.android.presentation

import android.view.View
import androidx.fragment.app.Fragment
import com.justclean.android.domain.Result


/**
 *   Created by Mohd Farhan on 12/10/2020.
 */

abstract class BaseFragment : Fragment() {

    var loaderView : View? = null;

    fun showResult(result: Result<Any>) {
        when(result) {
            is Result.Success -> {
                loader(false)
                populateUi(result.data)
            }
            is Result.Error -> {
                showSnackBar(result.error)
                loader(false)
            }
            is Result.Loading -> {
                loader(true)
            }
        }
    }

    open fun populateUi(result : Any){

    }


    fun loader(show: Boolean){
        if(show)
            loaderView?.visibility = View.VISIBLE
        else
            loaderView?.visibility = View.GONE
    }

    fun showSnackBar(message : String){
        (activity as BaseActivity).showSnackBar(message)
    }

}