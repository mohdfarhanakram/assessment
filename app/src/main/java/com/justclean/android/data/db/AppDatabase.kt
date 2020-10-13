package com.justclean.android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.justclean.android.domain.Fav
import com.justclean.android.domain.Post

/**
 *   Created by Mohd Farhan on 12/10/2020.
 */
@Database(entities = [Post::class, Fav::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun favDao(): FavDao
}