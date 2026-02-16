package com.example.mvvmpostlast.data.repository

import android.util.Log
import com.example.mvvmpostlast.domain.repository.IAirplaneRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AirplaneRepositoryImpl @Inject constructor () : IAirplaneRepository {
    private val _airplaneMode = MutableStateFlow(false)
    override val airplaneMode : StateFlow<Boolean> = _airplaneMode

    init {
        Log.d("AIRPLANE_DEBUG", "Repository created: ${this.hashCode()}")
    }

    override fun update(state: Boolean) {
        Log.d("AIRPLANE_DEBUG", "Repository update = $state")
        _airplaneMode.value = state
    }

}