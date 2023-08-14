package com.rizkir.promoapp.features.detail_promo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizkir.promoapp.common.Resource
import com.rizkir.promoapp.domain.entities.PromoEntity
import com.rizkir.promoapp.domain.usecases.GetPromoByIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class DetailPromoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    useCase: GetPromoByIdUseCase
) : ViewModel() {

    private val promoId = savedStateHandle.get<Int>("promoId") ?: 0


    val uiState: StateFlow<DetailPromoUiState> = uiState(useCase, promoId)

        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DetailPromoUiState.Loading(isLoading = true)
        )

    private fun uiState(useCase: GetPromoByIdUseCase, promoId: Int): Flow<DetailPromoUiState> = flow {
        val result = useCase.invoke(promoId)

        result.collect { resource ->
            when (resource) {
                is Resource.Loading -> {
                    emit(DetailPromoUiState.Loading(isLoading = false))
                }

                is Resource.Success -> {
                    emit(DetailPromoUiState.Success(resource.data))
                }

                is Resource.Error -> {
                    emit(DetailPromoUiState.Error(resource.message.toString()))
                }
            }
        }

    }

}

sealed interface DetailPromoUiState {
    data class Loading(val isLoading: Boolean) : DetailPromoUiState
    data class Success(val data: PromoEntity?) : DetailPromoUiState
    data class Error(val error: String) : DetailPromoUiState
}