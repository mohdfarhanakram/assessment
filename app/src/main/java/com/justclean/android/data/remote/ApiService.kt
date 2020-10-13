package com.justclean.android.data.remote

import com.justclean.android.domain.Comment
import com.justclean.android.domain.Post
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *   Created by Mohd Farhan on 12/10/2020.
 */
interface ApiService {

    @GET("posts")
    fun getPostList() : Observable<List<Post>>

    @GET("posts/{id}/comments")
    fun getPostComment(@Path("id") id : String) : Observable<List<Comment>>


}