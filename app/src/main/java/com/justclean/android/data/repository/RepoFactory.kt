package com.justclean.android.data.repository

import com.justclean.android.data.db.AppDatabase
import com.justclean.android.data.db.FavDao
import com.justclean.android.data.db.PostDao
import com.justclean.android.data.remote.ApiService
import javax.inject.Inject

/**
 *   Created by Mohd Farhan on 12/10/2020.
 */
class RepoFactory @Inject constructor (
    private val _apiService: ApiService,
    private val appDatabase: AppDatabase
){

    val apiService: ApiService get() = _apiService
    val postDao: PostDao get() = appDatabase.postDao()
    val favDao: FavDao get() = appDatabase.favDao()
}