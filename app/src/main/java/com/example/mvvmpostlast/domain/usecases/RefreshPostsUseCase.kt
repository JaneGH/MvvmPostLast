package com.example.mvvmpostlast.domain.usecases

import com.example.mvvmpostlast.domain.repository.IPostRepository
import javax.inject.Inject


class RefreshPostsUseCase @Inject constructor(
    private val repository: IPostRepository
) {
    suspend operator fun invoke() {
        repository.refreshPosts()
    }
}