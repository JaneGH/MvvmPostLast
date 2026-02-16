package com.example.mvvmpostlast.presentation.posts

import android.content.Intent
import android.content.IntentFilter
import android.view.LayoutInflater
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mvvmpostlast.broadcast.AirplaneModeReceiver
import com.example.mvvmpostlast.databinding.ViewHeaderBinding


@Composable
fun PostsScreen( onPostClick: (String) -> Unit,
                 vm: PostViewModelContract = hiltViewModel<PostViewModel>()) {

    val uiState by vm.postUiState.collectAsStateWithLifecycle()
    val airplaneMode by vm.airplaneMode.collectAsStateWithLifecycle()



    LaunchedEffect(Unit) {
        vm.onScreenOpened()
    }

    LaunchedEffect(airplaneMode) {
        if (!airplaneMode) {
            vm.getPosts()
        }
    }


    Column(
        Modifier.systemBarsPadding(),
    ) {

        if (uiState.progress in 1..99) {
            LinearProgressIndicator(
                progress = { uiState.progress / 100f },
                modifier = Modifier.fillMaxWidth()
                    .testTag("progress")
            )
        }




            Column(
                modifier = Modifier.fillMaxWidth(),

            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center

                ) {
                    Button(
                        modifier = Modifier
                            .testTag("btnGetPost"),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),

                        onClick = { vm.getPosts() },

                        ) {
                        Text("Get posts")
                    }

                }

                AndroidView(
                    factory = { context ->
                        ViewHeaderBinding.inflate(
                            LayoutInflater.from(context),
                            null,
                            false
                        ).root
                    },
                    update = { view ->
//                         val binding = ViewHeaderBinding.bind(view)
//                         binding.headerText.text = "Posts1"
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("xmlHeader")
                )


            }
        when {
            uiState.error != null -> {
                Text(
                    modifier = Modifier
                        .testTag("textError"),
                    text = "Error ${uiState.error}")
            }

            uiState.data != null -> {
                LazyColumn(
                    modifier = Modifier
                        .testTag("postList")
                        .weight(1f)
                ) {
                    items(uiState.data!!) { post ->
                        Column(
                            Modifier.clickable(onClick = {
                                onPostClick("23")
                            })
                        ) {
                            Text(
                                text = post.title,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = post.body,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                }

            }

            uiState.loading -> {
                CircularProgressIndicator()
            }


        }
    }
}

