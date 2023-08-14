package com.rizkir.promoapp.common.components

import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationAppBar(title: String) {
    TopAppBar(title = { Text(text = title) }, colors = TopAppBarDefaults.mediumTopAppBarColors(
        containerColor = Color.Cyan
    ), modifier = Modifier.background(Color.Transparent))

}
