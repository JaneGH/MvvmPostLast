package com.example.mvvmpostlast.presentation.detail

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mvvmpostlast.PostApp
import com.example.mvvmpostlast.WebAppBridge
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.coroutines.tasks.await

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun DetailScreen(
    onAction: (String) -> Unit,
    onListClick:()->Unit,
    viewModel: CmsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    var showNewUI by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.onScreenOpened()
        viewModel.load()
    }

    LaunchedEffect(Unit) {
        val remoteConfig =
            (context.applicationContext as PostApp).remoteConfig

        try {
            remoteConfig.fetchAndActivate().await()
            showNewUI = remoteConfig.getBoolean("home_new_ui")
        } catch (e: Exception) {
            showNewUI = false
            Log.d("!!!","${e.message}")
        }
    }


    when (val state = uiState) {
        is DetailPostUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is DetailPostUiState.Error -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "Error: ${state.message}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        is DetailPostUiState.Success -> {
            Column(
                modifier = Modifier.systemBarsPadding()
            ) {
                if (showNewUI) {
                    Text(
                        color = Color.Blue,
                        modifier = Modifier.padding(start = 16.dp),
                        text = "New ui features turn on"
                    )
                }else{
                    Text(
                        color = Color.Red,
                        modifier = Modifier.padding(start = 16.dp),
                        text = "New ui features turn off")
                }

                Button(
                    modifier = Modifier.padding(start = 16.dp),
                    onClick = { throw RuntimeException("Test Crash from Post App")}
                ) {
                    Text("Test crash")
                }

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .systemBarsPadding()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.page.blocks) { block ->
                        CmsBlockRenderer(
                            block = block, onAction = onAction
                        )
                    }
                }

                AndroidView(
                    modifier = Modifier.weight(1f),
                    factory = { context ->
                        WebView(context).apply {
                            settings.javaScriptEnabled = true
                            settings.domStorageEnabled = true
                            webViewClient = WebViewClient()
                            addJavascriptInterface(
                                WebAppBridge {
                                    onListClick()
                                },
                                "AndroidBridge"
                            )

                            loadUrl("file:///android_asset/post_detail.html")

                        }
                    }
                )


            }
        }
    }
}