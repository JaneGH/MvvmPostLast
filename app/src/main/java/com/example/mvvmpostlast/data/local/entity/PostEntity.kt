package com.example.mvvmpostlast.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("post")
data class PostEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val body: String,
    var title: String,
    val userId: Int
)