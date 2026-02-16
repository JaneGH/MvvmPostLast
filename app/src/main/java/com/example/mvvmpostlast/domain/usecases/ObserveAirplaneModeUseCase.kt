package com.example.mvvmpostlast.domain.usecases

import android.util.Log
import com.example.mvvmpostlast.domain.repository.IAirplaneRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ObserveAirplaneModeUseCase  @Inject constructor( private  val repository: IAirplaneRepository) {
    operator fun invoke(): Flow<Boolean> {
        return repository.airplaneMode
            .onEach {
                Log.d("AIRPLANE_DEBUG", "UseCase emit = $it")
            }
    }
}