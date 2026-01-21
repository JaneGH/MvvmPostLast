package com.example.mvvmpostlast

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.mvvmpostlast.navigation.AppNavGraph
import com.example.mvvmpostlast.ui.theme.MvvmPostLastTheme
import com.example.mvvmpostlast.presentation.posts.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MvvmPostLastTheme {
                val navController = rememberNavController()
                val viewModel: PostViewModel = hiltViewModel()
                LaunchedEffect(Unit) {
                    viewModel.onAppStarted()
                }
                Scaffold(modifier = Modifier.fillMaxSize()) {
                     AppNavGraph(navController = navController)
                }
            }
        }
    }
}


