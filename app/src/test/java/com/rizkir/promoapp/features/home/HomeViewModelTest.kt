package com.rizkir.promoapp.features.home

import com.rizkir.promoapp.common.Resource
import com.rizkir.promoapp.common.testing.MainDispatcherRule
import com.rizkir.promoapp.data.datasources.remote_datasource.dto.FormatsDto
import com.rizkir.promoapp.data.datasources.remote_datasource.dto.ImgDto
import com.rizkir.promoapp.data.datasources.remote_datasource.dto.MediumDto
import com.rizkir.promoapp.data.datasources.remote_datasource.dto.PromoDto
import com.rizkir.promoapp.data.datasources.remote_datasource.dto.SmallDto
import com.rizkir.promoapp.data.datasources.remote_datasource.dto.ThumbnailDto
import com.rizkir.promoapp.domain.entities.Formats
import com.rizkir.promoapp.domain.entities.Img
import com.rizkir.promoapp.domain.entities.Medium
import com.rizkir.promoapp.domain.entities.PromoEntity
import com.rizkir.promoapp.domain.entities.Small
import com.rizkir.promoapp.domain.entities.Thumbnail
import com.rizkir.promoapp.domain.repositories.PromoRepository
import com.rizkir.promoapp.domain.usecases.GetAllPromoUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.junit.jupiter.MockitoExtension

@Extensions(
    value = [
        ExtendWith(MainDispatcherRule::class),
        ExtendWith(MockitoExtension::class)
    ]
)
class HomeViewModelTest {

    @Mock
    private lateinit var repo: PromoRepository

    private lateinit var useCase: GetAllPromoUseCase

    private lateinit var viewModel: HomeViewModel

    @BeforeEach
    fun beforeEach() {
        useCase = GetAllPromoUseCase(repo)
        viewModel = HomeViewModel(useCase)
    }



    @Test
    @DisplayName("Should Get Success From UseCase and Is Not Empty")
    fun getAllPromoSuccessWithData() = runTest {


         // Arrange
        Mockito.lenient().`when`(repo.getAllPromo()).thenReturn(flowOf(Resource.Success(sampleEntity)))

         // Act
        viewModel.uiState.value


         // Assert
        val job = launch {
            viewModel.uiState.collect {
                if (it is UiState.Success) {
                    println("TestTestTest: ${it.data?.size}")
                    assertNotNull(it.data)
                    assertNotEquals(0, it.data?.size)
                }
            }
        }


        delay(1000L)

        Mockito.verify(repo).getAllPromo()

        job.cancel()



    }

    @Test
    @DisplayName("Should Get Success From UseCase and Is Empty")
    fun getAllPromoSuccessWithEmptyData() = runTest {

        // Arrange
        Mockito.`when`(repo.getAllPromo()).thenReturn(flowOf(Resource.Success(emptyList())))

        // Act
        viewModel.uiState.value

        // Assert
        val job = launch {
            viewModel.uiState.collect {
                if (it is UiState.Success) {
                    assertNotNull(it.data?.isEmpty())
                    assertTrue(it.data?.isEmpty() ?: false)
                }
            }
        }

        delay(1000L)

        Mockito.verify(repo).getAllPromo()

        job.cancel()

    }

    @Test
    @DisplayName("Should Get Error From UseCase")
    fun getAllPromoError() = runTest {

        val expected = Exception("Error")

        // Arrange
        Mockito.`when`(repo.getAllPromo()).thenReturn(flowOf(Resource.Error(expected.message ?: "")))

        // Act
        viewModel.uiState.value

        // Assert
        val job = launch {
            viewModel.uiState.collect {
                if (it is UiState.Error) {
                    assertNotNull(it.error)
                    assertEquals(expected.message, it.error)
                }
            }
        }

        delay(1000L)

        Mockito.verify(repo).getAllPromo()

        job.cancel()

    }

    @Test
    @DisplayName("Should Get Loading From UseCase and Dismiss or False")
    fun getAllPromoLoadingDismiss() = runTest {

        // Arrange
        Mockito.`when`(repo.getAllPromo()).thenReturn(flowOf(Resource.Loading(true)))

        // Act
        viewModel.uiState.value

        // Assert
        val job = launch {
            viewModel.uiState.collect {
                if (it is UiState.Loading) {
                    assertNotNull(it)
                    assertEquals(false, it.isLoading)
                }
            }
        }

        delay(1000L)

        Mockito.verify(repo).getAllPromo()

        job.cancel()

    }


    val sampleDto = listOf<PromoDto>(
        PromoDto(
            id = 8,
            img = ImgDto(
                id = 3,
                formats = FormatsDto(
                    thumbnail = ThumbnailDto(
                        "dqw",
                        "dqw",
                        "dqw",
                        "dqw",
                        "dqw",
                        hash = "dqw",
                        url = "dqw",
                        width = 12,
                        height = 12
                    ),
                    medium = MediumDto(
                        "dqw",
                        "dqw",
                        "dqw",
                        "dqw",
                        "dqw",
                        hash = "dqw",
                        url = "dqw",
                        width = 12,
                        height = 12
                    ),
                    small = SmallDto(
                        "dqw",
                        "dqw",
                        "dqw",
                        "dqw",
                        "dqw",
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
                providerMetadata = "dqw",
                createdAt = "dqw",
                updatedAt = "dqw"

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
            created_At = "143",
            updatedAt = "532",
            publishedAt = "4324",

            ),
        PromoDto(
            id = 65,
            img = ImgDto(
                id = 4,
                formats = FormatsDto(
                    thumbnail = ThumbnailDto(
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
                    medium = MediumDto(
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
                    small = SmallDto(
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
                providerMetadata = "dqw",
                createdAt = "dqw",
                updatedAt = "dqw",
                name = "dqw",
                caption = "dqw",

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
            created_At = "143",
            updatedAt = "532",
            publishedAt = "4324",

            )
    )
    val sampleEntity = listOf<PromoEntity>(
        PromoEntity(
            id = 8,
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


            ),
        PromoEntity(
            id = 9,
            img = Img(
                id = 98,
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
    )

}