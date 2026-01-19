package com.example.mvvmpostlast.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.mvvmpostlast.view.DetailScreen
import com.example.mvvmpostlast.view.PostsScreen


@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController,
        startDestination = Screen.PostsList.route)
    {
        composable(Screen.PostsList.route) {
            PostsScreen(
                onPostClick = { postId ->
                    navController.navigate("post_detail/$postId")
                }
            )
        }

        composable(
            route = Screen.PostDetail.route,
            arguments = listOf(
                navArgument("postId"){
                    type = NavType.StringType
                }
            ),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "myapp://post/{postId}"
                }
            )



        ) {
            entry->
            DetailScreen(
                postId = entry.arguments?.getString("postId")!!
            )
        }
    }

}
