package com.rizkir.promoapp.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.rizkir.promoapp.common.components.ApplicationAppBar
import com.rizkir.promoapp.common.components.CircularProgress
import com.rizkir.promoapp.domain.entities.PromoEntity


@Composable
internal fun HomeScreen(
    uiState: UiState, onPromoClick: (Int) -> Unit
) {


    HomeScreen(uiState = uiState, hasPromoList = { data, modifier ->
        HomeItem(
            data = data, onPromoClick = onPromoClick, modifier = modifier
        )
    })


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    hasPromoList: @Composable (data: PromoEntity, modifier: Modifier) -> Unit, uiState: UiState
) {
    Scaffold(topBar = { ApplicationAppBar("Promo App") }) {
        val modifier = Modifier.padding(it)

        LoadingComponent(uiState, loadingContent = { CircularProgress() }) {
            when (uiState) {
                is UiState.Success -> {


                    LazyColumn(
                        modifier = modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {

                        uiState.data?.count()?.let { it1 ->
                            items(count = it1) { index ->
                                val dataItem = uiState.data[index]
                                hasPromoList(data = dataItem, modifier = Modifier.fillMaxWidth())
                            }
                        }


                    }

                }

                is UiState.Error -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = uiState.error)
                    }
                }

                else -> Unit
            }


        }
    }
}

@Composable
fun LoadingComponent(
    loadingState: UiState,
    loadingContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    if (loadingState is UiState.Loading) {
        loadingContent()
    } else {
        content()
    }

}


@Composable
internal fun HomeItem(
    data: PromoEntity?, onPromoClick: (Int) -> Unit, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .clickable { onPromoClick(data?.id ?: 0) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(size = 4.dp),

        ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current).data(data?.img?.formats?.medium?.url)
                .size(Size.ORIGINAL).crossfade(true).build(),
        )

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(4.dp)),
            painter = painter,
            contentScale = ContentScale.Crop,
            contentDescription = data?.namePromo
        )


    }
}


