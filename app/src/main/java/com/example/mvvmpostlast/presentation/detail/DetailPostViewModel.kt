package com.example.mvvmpostlast.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmpostlast.analytics.AnalyticsManager
import com.example.mvvmpostlast.domain.usecases.GetCmsPageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CmsViewModel @Inject constructor(
    private val getCmsPage: GetCmsPageUseCase,
    private val analyticsManager: AnalyticsManager
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailPostUiState>(DetailPostUiState.Loading)
    val uiState: StateFlow<DetailPostUiState> = _uiState

    fun load() {
        _uiState.value = DetailPostUiState.Loading

        viewModelScope.launch {
            val result = getCmsPage()
            result
                .onSuccess { _uiState.value = DetailPostUiState.Success(it) }
                .onFailure { _uiState.value = DetailPostUiState.Error(it.message ?: "Unknown error") }
        }
    }

    fun onScreenOpened(){
        analyticsManager.logScreen("DetailScreen")
    }
}
