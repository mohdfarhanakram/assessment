package com.justclean.android.presentation

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

/**
 *   Created by Mohd Farhan on 12/10/2020.
 */

abstract class BaseFragment : Fragment() {

    fun showSnackBar(message : String){
        (activity as BaseActivity).showSnackBar(message)
    }
}