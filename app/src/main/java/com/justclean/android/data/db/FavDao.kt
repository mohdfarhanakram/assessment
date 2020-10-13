package com.justclean.android.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.justclean.android.domain.Fav
import com.justclean.android.domain.Post
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 *   Created by Mohd Farhan on 13/10/2020.
 */

@Dao
interface FavDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFav(fav: Fav)  : Completable

    @Query("SELECT * FROM Fav")
    fun getFavList(): Flowable<List<Fav>>
}