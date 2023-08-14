package com.rizkir.promoapp.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizkir.promoapp.common.Resource
import com.rizkir.promoapp.domain.entities.PromoEntity
import com.rizkir.promoapp.domain.usecases.GetAllPromoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.flow.flow

import kotlinx.coroutines.flow.stateIn

import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCase: GetAllPromoUseCase
) : ViewModel() {


    val uiState: StateFlow<UiState> = uiState(useCase)
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Loading(isLoading = true)
        )


    private fun uiState(useCase: GetAllPromoUseCase): Flow<UiState> = flow {
        val result = useCase.invoke()

        result.collect { resource ->
            when (resource) {
                is Resource.Loading -> {
                    emit(UiState.Loading(isLoading = false))
                }

                is Resource.Success -> {
                    emit(UiState.Success(resource.data))
                }

                is Resource.Error -> {
                    emit(UiState.Error(resource.message.toString()))
                }
            }
        }
    }


}

sealed interface UiState {
    data class Loading(val isLoading: Boolean) : UiState
    data class Success(val data: List<PromoEntity>?) : UiState
    data class Error(val error: String) : UiState
}
