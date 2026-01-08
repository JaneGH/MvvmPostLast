package com.example.mvvmpostlast.data.repository

import com.example.mvvmpostlast.data.local.dao.PostDao
import com.example.mvvmpostlast.data.local.entity.PostEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostLocalDataSource @Inject constructor (val dao: PostDao) {
    suspend fun savePost(listPost:List<PostEntity>) {
        dao.insertAll(listPost)
    }

    fun getPosts(): Flow<List<PostEntity>> {
        return dao.observeAll()
    }
}