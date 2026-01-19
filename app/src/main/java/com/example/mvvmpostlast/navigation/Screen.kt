package com.example.mvvmpostlast.navigation

sealed class Screen(val route: String){
    object PostsList : Screen("posts")
    object PostDetail : Screen("post_detail/{postId}") {
        fun createRout(postId: String) : String {
            return "post_detail/$postId"
        }
    }
}