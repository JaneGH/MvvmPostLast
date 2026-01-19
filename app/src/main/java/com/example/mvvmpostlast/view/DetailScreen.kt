package com.example.mvvmpostlast.view

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DetailScreen (postId: String) {
    Text("Detail screen $postId")
    Log.d("DeepLink", "postId = $postId")
}