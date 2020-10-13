package com.justclean.android.data.db

import androidx.room.*
import com.justclean.android.domain.Fav
import com.justclean.android.domain.Post
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 *   Created by Mohd Farhan on 12/10/2020.
 */

@Dao
interface PostDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(postList: List<Post>) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: Post)  : Completable

    @Query("SELECT * FROM Post")
    fun getPostList(): Flowable<List<Post>>

}