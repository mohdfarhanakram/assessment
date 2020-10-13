package com.justclean.android.domain

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 *   Created by Mohd Farhan on 12/10/2020.
 */

@Entity
data class Fav(
    @PrimaryKey val favId : String,
    val title : String,
    val body : String,
)