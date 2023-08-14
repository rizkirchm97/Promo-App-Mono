package com.rizkir.promoapp.features.detail_promo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * created by RIZKI RACHMANUDIN on 14/08/2023
 */

@Composable
fun DetailPromoRoute(
    viewModel: DetailPromoViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {

    val uiState: DetailPromoUiState by viewModel.uiState.collectAsStateWithLifecycle()

    DetailPromoScreen(
        uiState = uiState,
        onNavigateBack = onNavigateBack
    )
}


