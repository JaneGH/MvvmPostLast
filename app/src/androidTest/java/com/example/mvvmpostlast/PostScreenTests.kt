package com.example.mvvmpostlast

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.mvvmpostlast.presentation.posts.PostUiState
import com.example.mvvmpostlast.presentation.posts.PostsScreen
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class PostsScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var vm: FakePostViewModel

    @Before
    fun setup() {
        vm = FakePostViewModel(PostUiState())
    }

    private fun setScreen(
        onPostClick: (String) -> Unit = {}
    ) {
        composeRule.setContent {
            PostsScreen(
                onPostClick = onPostClick,
                vm = vm
            )
        }
    }

    @Test
    fun button_isDisplayed() {
        setScreen()

        composeRule
            .onNodeWithTag("btnGetPost")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun clickButton_loadsPosts() {
        setScreen()

        composeRule
            .onNodeWithTag("btnGetPost")
            .performClick()

        composeRule
            .onNodeWithTag("postList")
            .assertIsDisplayed()
    }

    @Test
    fun xmlHeader_isDisplayed() {
        setScreen()

        composeRule
            .onNodeWithTag("xmlHeader")
            .assertIsDisplayed()

//        onView(withId(R.id.headerText))
//            .check(matches(isDisplayed()))
        onView(withId(R.id.headerText))
            .check(matches(withText("Posts:")))
    }

    @Test
    fun clickOnPost_callsOnPostClick() {
        var clickedId: String? = null
        vm.getPosts()

        setScreen { clickedId = it }

        composeRule
            .onNodeWithText("Title")
            .performClick()

        assertEquals("23", clickedId)
    }
}
