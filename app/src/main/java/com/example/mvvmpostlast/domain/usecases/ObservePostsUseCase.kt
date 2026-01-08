package com.example.mvvmpostlast.domain.usecases

import com.example.mvvmpostlast.domain.model.Post
import com.example.mvvmpostlast.domain.repository.IPostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObservePostsUseCase @Inject constructor(
    private val repository: IPostRepository
) {
    operator fun invoke(): Flow<List<Post>> {
        return repository.observePosts()
    }
}
