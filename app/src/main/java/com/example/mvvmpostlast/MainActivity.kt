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
import com.example.mvvmpostlast.analytics.AnalyticsManager
import com.example.mvvmpostlast.navigation.AppNavGraph
import com.example.mvvmpostlast.ui.theme.MvvmPostLastTheme
import com.example.mvvmpostlast.presentation.posts.PostViewModel
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var analytics: FirebaseAnalytics
    @Inject
    lateinit var analyticsManager: AnalyticsManager
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        analytics = Firebase.analytics
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


