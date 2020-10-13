package com.justclean.android.domain

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 *   Created by Mohd Farhan on 13/10/2020.
 */
data class Comment(
    @SerializedName("id") @PrimaryKey val id : String,
    @SerializedName("postId") val postId : String,
    @SerializedName("name") val name : String,
    @SerializedName("email") val email : String,
    @SerializedName("body") val body : String
)