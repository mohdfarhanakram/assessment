package com.justclean.android.data.repository

import com.justclean.android.domain.Comment
import com.justclean.android.domain.Fav
import com.justclean.android.domain.Post
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 *   Created by Mohd Farhan on 12/10/2020.
 */
interface IRepository {


    fun insertPostList(postList: List<Post>) : Completable

    fun getPostList() : Observable<List<Post>>

    fun getPostListFromDb() : Observable<List<Post>>

    fun getCommentList(id : String) : Observable<List<Comment>>

    fun addFav(post: Post) : Completable

    fun addPost(post: Post) : Completable

    fun getFavList() : Flowable<List<Fav>>
}