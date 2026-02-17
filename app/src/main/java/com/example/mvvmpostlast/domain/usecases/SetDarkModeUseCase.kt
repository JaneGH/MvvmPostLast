package com.example.mvvmpostlast.domain.usecases

import com.example.mvvmpostlast.domain.repository.IThemeRepository
import javax.inject.Inject

class SetDarkModeUseCase @Inject constructor(
    private val repository: IThemeRepository
) {

    suspend operator fun invoke(enabled: Boolean) {
        repository.setDarkMode(enabled)
    }

}