package com.rizkir.promoapp.features.detail_promo

import androidx.lifecycle.SavedStateHandle
import com.rizkir.promoapp.common.Resource
import com.rizkir.promoapp.common.testing.MainDispatcherRule
import com.rizkir.promoapp.domain.entities.Formats
import com.rizkir.promoapp.domain.entities.Img
import com.rizkir.promoapp.domain.entities.Medium
import com.rizkir.promoapp.domain.entities.PromoEntity
import com.rizkir.promoapp.domain.entities.Small
import com.rizkir.promoapp.domain.entities.Thumbnail
import com.rizkir.promoapp.domain.repositories.PromoRepository
import com.rizkir.promoapp.domain.usecases.GetPromoByIdUseCase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension


@Extensions(
    value = [
        ExtendWith(MockitoExtension::class),
        ExtendWith(MainDispatcherRule::class)
    ]
)
class DetailPromoViewModelTest {

    @Mock
    private lateinit var repo: PromoRepository

    @Mock
    private lateinit var saveStateHandle: SavedStateHandle

    private lateinit var useCase: GetPromoByIdUseCase

    private lateinit var viewModel: DetailPromoViewModel

    @BeforeEach
    fun setUp() {
        useCase = GetPromoByIdUseCase(repo)
        saveStateHandle = mock(SavedStateHandle::class.java)
        viewModel = DetailPromoViewModel(useCase = useCase, savedStateHandle = saveStateHandle)
    }

    @Test
    @DisplayName("Should Get Success From UseCase and Is Not Empty")
    fun testGetPromoByIdSuccessAndIsNotEmpty() = runTest {

        // Arrange
        Mockito.lenient().`when`(repo.getPromoById(99)).thenReturn(flowOf(Resource.Success(sampleEntity)))

        // Act
        viewModel.uiState.value

        // Assert
        val job = launch {
            viewModel.uiState.collect { result ->
                when (result) {
                    is DetailPromoUiState.Success -> {
                        assertNotNull(result.data)
                        assertTrue(result.data?.id == 0)
                    } else -> Unit
                }

            }
        }

        delay(1000L)


        job.cancel()


    }

    @Test
    @DisplayName("SavedStateHandle Should Not Null and Return Given Value")
    fun testSavedStateHandleShouldNotNullAndReturnGivenValue() = runTest {

        // Arrange
        Mockito.lenient().`when`(saveStateHandle.get<Int>("promoId")).thenReturn(1)

        // Act
        val result = saveStateHandle.get<Int>("promoId")

        // Assert
        assertNotNull(result)
        assertTrue(result == 1)

    }


    @Test
    @DisplayName("Should Get Error From UseCase with Given Null Id")
    fun testGetPromoByIdErrorBecauseWithGivenNullId() = runTest {

        // Arrange
        Mockito.`when`(repo.getPromoById(null)).thenReturn(flowOf(Resource.Error("Invalid Id")))

        // Act
        viewModel.uiState.value

        // Assert
        val job = launch {
            viewModel.uiState.collect { result ->
                when (result) {
                    is DetailPromoUiState.Error -> {
                        assertNotNull(result.error)
                        assertTrue(result.error == "Invalid Id")
                    }

                    else -> Unit
                }

            }
        }

        delay(1000L)

        job.cancel()
    }

    val sampleEntity = PromoEntity(
        id = 99,
        img = Img(
            id = 3,
            formats = Formats(
                thumbnail = Thumbnail(
                    ext = "dqw",
                    size = "dqw",
                    mime = "dqw",
                    name = "dqw",
                    path = "dqw",
                    hash = "dqw",
                    url = "dqw",
                    width = 12,
                    height = 12
                ),
                medium = Medium(
                    ext = "dqw",
                    size = "dqw",
                    mime = "dqw",
                    name = "dqw",
                    path = "dqw",
                    hash = "dqw",
                    url = "dqw",
                    width = 12,
                    height = 12
                ),
                small = Small(
                    ext = "dqw",
                    size = "dqw",
                    mime = "dqw",
                    name = "dqw",
                    path = "dqw",
                    hash = "dqw",
                    url = "dqw",
                    width = 12,
                    height = 12
                )
            ),
            url = "dqw",
            width = 1,
            height = 1,
            hash = "dsaf",
            ext = "dwqfwq",
            mime = "dqw",
            size = 2.02,
            previewUrl = "dqw",
            alternativeText = "dwqfwq",
            provider = "dqw",
            provider_metadata = "dqw",
            created_at = "dqw",
            updated_at = "dqw",
            caption = "dqw",
            name = "dqw",

            ),
        title = "dw",
        desc = "rwqcrqtq",
        descPromo = "grega",
        lokasi = "rrqwrqw",
        namePromo = "fcqwwe",
        nama = "cqrq",
        alt = "gewg",
        latitude = "1.0",
        longitude = "1.0",
        count = 9,
        createdAt = "4234325",
        created_at = "143",
        updated_at = "532",
        published_at = "4324",


        )



}