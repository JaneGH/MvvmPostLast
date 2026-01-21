package com.example.mvvmpostlast.presentation.detail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mvvmpostlast.domain.model.CmsBlock

@Composable
fun CmsBlockRenderer(
    block: CmsBlock,
    onAction: (String) -> Unit
) {
    when (block.type) {
        "header" -> Text(
            text = block.text,
            style = MaterialTheme.typography.headlineLarge
        )

        "paragraph" -> Text(
            text = block.text,
            style = MaterialTheme.typography.bodyLarge
        )

        "image" -> AsyncImage(
            model = block.url,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        "button" -> Button(onClick = { onAction(block.action) }) {
            Text(block.text)
        }

        else -> Text("Unsupported block type: ${block.type}")
    }
}
