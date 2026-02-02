package com.example.mvvmpostlast

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.mvvmpostlast.domain.model.Post
import com.example.mvvmpostlast.domain.usecases.ObservePostsUseCase
import com.example.mvvmpostlast.domain.usecases.ObserveUploadProgressUseCase
import com.example.mvvmpostlast.domain.usecases.StartUploadWorkUseCase
import com.example.mvvmpostlast.presentation.posts.PostViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import io.mockk.verify


@OptIn(ExperimentalCoroutinesApi::class)
class PostViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    lateinit var observePosts: ObservePostsUseCase
    lateinit var observeUploadProgress: ObserveUploadProgressUseCase
    lateinit var startUploadWorkUseCase: StartUploadWorkUseCase

    private lateinit var viewModel: PostViewModel

    @Before
    fun setUp() {
        observePosts = mockk()
        observeUploadProgress = mockk()
        startUploadWorkUseCase = mockk(relaxed = true)

        every { observeUploadProgress() } returns flowOf(0)

        viewModel = PostViewModel(
            observePosts = observePosts,
            observeUploadProgress = observeUploadProgress,
            startUploadWorkUseCase = startUploadWorkUseCase,
            analyticsManager = mockk(relaxed = true)
        )
    }


    @Test
    fun onAppStarted_startsUploadWork() {
        viewModel.onAppStarted()
        verify{ startUploadWorkUseCase.invoke() }
    }


    @Test
    fun uploadProgress_updatesUiState() = runTest {
        every { observeUploadProgress() } returns flowOf(10, 50, 100)

        viewModel = PostViewModel(
            observePosts,
            observeUploadProgress,
            startUploadWorkUseCase,
            analyticsManager = mockk(relaxed = true)
        )

//        advanceUntilIdle()

//        assertEquals(100, viewModel.postUiState.value.progress)
//        with Turbine
        viewModel.postUiState.test {
            assertEquals(0, awaitItem().progress)
            assertEquals(10, awaitItem().progress)
            assertEquals(50, awaitItem().progress)
            assertEquals(100, awaitItem().progress)
            cancelAndIgnoreRemainingEvents()         // close Turbine
        }
    }


    @Test
    fun getPosts_success_updatesState() = runTest {
        val fakePosts = listOf(
            Post(id = 1, title = "Title 1", body = "Body 1", userId = 1),
            Post(id = 2, title = "Title 2", body = "Body 2", userId = 3),
        )

        every { observePosts() } returns flowOf(fakePosts)


//        viewModel.getPosts()
//
//        assertTrue(viewModel.postUiState.value.loading)
//
//        advanceUntilIdle()
//
//        val state = viewModel.postUiState.value
//        assertFalse(state.loading)
//        assertEquals(fakePosts, state.data)
//        assertNull(state.error)

        // with Turbine
        viewModel.postUiState.test {
            val initial = awaitItem()
            assertFalse(initial.loading)

            viewModel.getPosts()

            val loading = awaitItem()
            assertTrue(loading.loading)

            val result = awaitItem()
            assertFalse(result.loading)
            assertEquals(fakePosts, result.data)
            assertNull(result.error)

            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun getPosts_error_updatesErrorState() = runTest {
        every { observePosts() } returns flow {
            throw RuntimeException("Network error")
        }


//        viewModel.getPosts()
//        advanceUntilIdle()
//
//        val state = viewModel.postUiState.value
//        assertFalse(state.loading)
//        assertEquals("Network error", state.error)
        viewModel.postUiState.test {
            awaitItem()

            viewModel.getPosts()

            awaitItem()
            val errorState = awaitItem()
            assertFalse(errorState.loading)
            assertEquals("Network error", errorState.error)

            cancelAndIgnoreRemainingEvents()
        }
    }
}
