package com.justclean.android.di.module

import com.justclean.android.di.qualifier.BaseUrlString
import com.justclean.android.di.qualifier.DbName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 *   Created by Mohd Farhan on 12/10/2020.
 */

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @BaseUrlString
    fun getBaseUrl() :String{
        return "https://jsonplaceholder.typicode.com/";
    }

    @Provides
    @DbName
    fun getDbName() :String{
        return "justclean.db";
    }
}