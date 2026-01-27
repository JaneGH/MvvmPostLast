package com.example.mvvmpostlast

import com.example.mvvmpostlast.data.remote.ApiService
import com.example.mvvmpostlast.data.source.PostRemoteDataSource
import com.example.mylasttrainproject.data.remote.PostDto
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class PostRemoteDataSourceTest {
    private val api: ApiService = mockk()
    private lateinit var dataSource: PostRemoteDataSource

    @Before
    fun setUp() {
         dataSource = PostRemoteDataSource(api)
    }


    @Test
    fun `check remote data source` () = runTest{
        val fakePost = listOf(
            PostDto(id = 1, title = "Title 1", body = "Body 1", userId = 1),
            PostDto(id = 2, title = "Title 2", body = "Body 2", userId = 2)
        )
        coEvery { api.getPost() } returns fakePost
        val result = dataSource.getPosts()
        assertEquals(fakePost, result)
    }
}