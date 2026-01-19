package com.example.mvvmpostlast.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun PostsScreen(onPostClick : (String) -> Unit) {
    val vm: PostViewModel = hiltViewModel()

    val uiState by vm.postUiState.collectAsStateWithLifecycle()
    Column(
        Modifier.systemBarsPadding(),
    ) {

        if (uiState.progress in 1..99) {
            LinearProgressIndicator(
                progress = { uiState.progress / 100f },
                modifier = Modifier.fillMaxWidth()
            )
        }


        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center

        ) {
            Button(
                onClick = { vm.getPosts() },

                ) {
                Text("Get posts")
            }
        }
        when {
            uiState.error != null -> {
                Text("Error ${uiState.error}")
            }

            uiState.data != null -> {
                LazyColumn() {
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

