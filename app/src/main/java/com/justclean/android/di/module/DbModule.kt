package com.justclean.android.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.justclean.android.data.db.AppDatabase
import com.justclean.android.data.db.PostDao
import com.justclean.android.di.qualifier.DbName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 *   Created by Mohd Farhan on 12/10/2020.
 */

@Module
@InstallIn(ApplicationComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context, @DbName dbName: String): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, dbName)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun providePostDao(appDatabase: AppDatabase): PostDao {
        return appDatabase.postDao()
    }

}