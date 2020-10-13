package com.justclean.android.presentation.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.justclean.android.R
import com.justclean.android.databinding.ActivityDashboardBinding
import com.justclean.android.presentation.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 *   Created by Mohd Farhan on 12/10/2020.
 */

@AndroidEntryPoint
class DashBoardActivity : BaseActivity(){

    lateinit var binding : ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        setUpNavigation()
    }

    private fun setUpNavigation(){

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?

        NavigationUI.setupWithNavController(
            binding.bottomNav,
            navHostFragment!!.navController
        )
    }
}