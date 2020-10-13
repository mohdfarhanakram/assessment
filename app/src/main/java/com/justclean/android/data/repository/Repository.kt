package com.justclean.android.data.repository

import com.justclean.android.domain.Comment
import com.justclean.android.domain.Fav
import com.justclean.android.domain.Post
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 *   Created by Mohd Farhan on 12/10/2020.
 */
class Repository  @Inject constructor (
    private val repoFactory: RepoFactory
) : IRepository{
    override fun insertPostList(postList: List<Post>): Completable {
       return repoFactory.postDao.insertAll(postList)
    }


    override fun getPostList() : Observable<List<Post>> {
        return repoFactory.apiService.getPostList()
    }

    override fun getPostListFromDb(): Flowable<List<Post>> {
        return repoFactory.postDao.getPostList()
    }

    override fun getCommentList(id :String): Observable<List<Comment>> {
        return repoFactory.apiService.getPostComment(id)
    }

    override fun addFav(post: Post): Completable {
        val fav = Fav(post.id,post.title,post.body)
        return repoFactory.favDao.insertFav(fav)
    }

    override fun addPost(post: Post): Completable {
        return repoFactory.postDao.insertPost(post)
    }

    override fun getFavList(): Flowable<List<Fav>> {
        return repoFactory.favDao.getFavList()
    }
}