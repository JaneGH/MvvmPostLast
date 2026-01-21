package com.example.mvvmpostlast.domain.model

data class CmsBlock(
    val type: String,
    val text: String = "",
    val url: String = "",
    val action: String = ""
)