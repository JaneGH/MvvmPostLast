package com.example.mvvmpostlast.data.repository

import com.example.mvvmpostlast.data.source.PostLocalDataSource
import com.example.mvvmpostlast.data.source.PostRemoteDataSource
import com.example.mvvmpostlast.data.mapper.toDomain
import com.example.mvvmpostlast.data.mapper.toEntity
import com.example.mvvmpostlast.domain.model.Post
import com.example.mvvmpostlast.domain.repository.IPostRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PostRepositoryImpl @Inject constructor(private val remote: PostRemoteDataSource,
                                             private val local: PostLocalDataSource
) : IPostRepository {

     override fun observePosts(): Flow<List<Post>> {
        return local.getPosts()
            .map { entities ->
                entities.map { it.toDomain() }
            }
    }

    override suspend fun refreshPosts() {
        val remotePosts = remote.getPosts()
        val entities = remotePosts.map { it.toEntity() }
        local.savePost(entities)
    }
}