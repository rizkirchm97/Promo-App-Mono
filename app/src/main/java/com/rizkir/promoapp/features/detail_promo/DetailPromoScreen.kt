package com.rizkir.promoapp.features.detail_promo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.rizkir.promoapp.common.components.ApplicationAppbar
import com.rizkir.promoapp.common.components.CircularProgress
import com.rizkir.promoapp.common.components.ErrorMessage
import com.rizkir.promoapp.domain.entities.PromoEntity

/**
 * created by RIZKI RACHMANUDIN on 14/08/2023
 */

@Composable
internal fun DetailPromoScreen(uiState: DetailPromoUiState, onNavigateBack: () -> Unit) {

    DetailPromoScreen(
        uiState = uiState,
        onNavigateBack = onNavigateBack,
        success = { data, modifier ->
            DetailPromoContent(
                data = data,
                modifier = modifier
            )

        },
        error = { message ->
            ErrorMessage(message)
        }
    )

}

@Composable
fun DetailPromoContent(data: PromoEntity?, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {


        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(shape = RoundedCornerShape(4.dp)),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(size = 4.dp)
        ){
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data?.img?.url)
                    .size(Size.ORIGINAL).crossfade(true).build(),
            )

            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(4.dp)),
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = data?.nama
            )
        }


        data?.let {
            DetailPromoItem(
                namePromo = it.nama,
                description = it.desc,
                expiredDate = it.createdAt,
                modifier = modifier.padding(16.dp)
            )
        }

    }
}

@Composable
fun DetailPromoItem(
    namePromo: String?,
    description: String?,
    expiredDate: String?,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {
        Text(
            text = namePromo ?: "",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = description ?: "")
        Spacer(modifier = Modifier.padding(8.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            Text(text = "Dibuat : $expiredDate" ?: "")
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPromoScreen(
    uiState: DetailPromoUiState,
    onNavigateBack: () -> Unit,
    success: @Composable (data: PromoEntity?, modifier: Modifier) -> Unit,
    error: @Composable (message: String) -> Unit,
) {
    Scaffold(
        topBar = {
            ApplicationAppbar(
                title = "Detail Promo",
                onClickBack = onNavigateBack
            )
        }
    ) {
        val modifier = Modifier.padding(it)

        LoadingScreen(
            uiState,
            loadingContent = {
                CircularProgress()
            },
            content = {
                when (uiState) {
                    is DetailPromoUiState.Success -> {

                        LazyColumn(modifier = modifier.fillMaxSize().background(Color.White)) {
                            item {
                                success(uiState.data, Modifier.fillMaxWidth())
                            }

                        }

                    }

                    is DetailPromoUiState.Error -> {
                        error(uiState.error)
                    }

                    else -> Unit
                }
            }
        )

    }
}

@Composable
fun LoadingScreen(
    uiState: DetailPromoUiState,
    loadingContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    if (uiState is DetailPromoUiState.Loading) {
        loadingContent()
    } else {
        content()
    }

}
