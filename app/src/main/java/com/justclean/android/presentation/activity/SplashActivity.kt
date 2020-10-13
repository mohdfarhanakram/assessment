package com.justclean.android.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.justclean.android.R
import com.justclean.android.databinding.LayoutSplashBinding
import com.justclean.android.presentation.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 *   Created by Mohd Farhan on 12/10/2020.
 */

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : LayoutSplashBinding = DataBindingUtil.setContentView(
            this,
            R.layout.layout_splash
        );

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, DashBoardActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}