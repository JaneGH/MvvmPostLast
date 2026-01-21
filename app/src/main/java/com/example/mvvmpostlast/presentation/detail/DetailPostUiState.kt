package com.example.mvvmpostlast.presentation.detail

import com.example.mvvmpostlast.domain.model.CmsPage

sealed class DetailPostUiState {
    data object Loading : DetailPostUiState()
    data class Success(val page: CmsPage) : DetailPostUiState()
    data class Error(val message: String) : DetailPostUiState()
}