package com.justclean.android.presentation

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

/**
 *   Created by Mohd Farhan on 12/10/2020.
 */

abstract class BaseActivity : AppCompatActivity() {


    fun showSnackBar(message : String){
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
    }

}



