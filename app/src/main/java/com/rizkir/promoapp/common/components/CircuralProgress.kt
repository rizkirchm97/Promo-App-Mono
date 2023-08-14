package com.rizkir.promoapp.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable

@Composable
fun CircularProgress() {
    Box(
        contentAlignment = androidx.compose.ui.Alignment.Center,
        modifier = androidx.compose.ui.Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}