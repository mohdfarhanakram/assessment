package com.justclean.android.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.justclean.android.domain.Post
import io.reactivex.Completable
import io.reactivex.Observable


/**
 *   Created by Mohd Farhan on 12/10/2020.
 */

@Dao
interface PostDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<Post>) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: Post)  : Completable

    @Query("SELECT * FROM Post")
    fun getPostList(): Observable<List<Post>>

}