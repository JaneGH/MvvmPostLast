package com.example.mvvmpostlast.domain.repository

import kotlinx.coroutines.flow.Flow

interface IAirplaneRepository {

    val airplaneMode: Flow<Boolean>

    fun update(state: Boolean)

}
