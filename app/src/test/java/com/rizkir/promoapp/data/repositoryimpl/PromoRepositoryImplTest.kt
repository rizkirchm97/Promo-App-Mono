package com.rizkir.promoapp.data.repositoryimpl

import com.rizkir.promoapp.common.Resource
import com.rizkir.promoapp.data.datasources.remote_datasource.PromoRemoteDataSource
import com.rizkir.promoapp.data.datasources.remote_datasource.dto.FormatsDto
import com.rizkir.promoapp.data.datasources.remote_datasource.dto.ImgDto
import com.rizkir.promoapp.data.datasources.remote_datasource.dto.MediumDto
import com.rizkir.promoapp.data.datasources.remote_datasource.dto.PromoDto
import com.rizkir.promoapp.data.datasources.remote_datasource.dto.SmallDto
import com.rizkir.promoapp.data.datasources.remote_datasource.dto.ThumbnailDto
import com.rizkir.promoapp.data.mapper.mapToEntity
import com.rizkir.promoapp.domain.entities.PromoEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@Extensions(
    value = [
        ExtendWith(MockitoExtension::class)
    ]
)
class PromoRepositoryImplTest {

    @Mock
    lateinit var remoteDataSource: PromoRemoteDataSource

    private lateinit var fakePromoRepositoryImpl: FakeRepositoryImpl

    @BeforeEach
    fun beforeEach() {
        fakePromoRepositoryImpl = FakeRepositoryImpl(remoteDataSource)
    }


    @Test
    @DisplayName("Should Get All Promo Success From Remote Datasource and Is Not Empty")
    fun testGetAllPromoSuccess() = runTest {

        // Arrange
        Mockito.`when`(remoteDataSource.getAllPromo())
            .thenReturn(flowOf(Resource.Success(expectedList)))

        // Act
        val result = fakePromoRepositoryImpl.getAllPromo()

        // Assert
        result.collect { resource ->

            when (resource) {
                is Resource.Success -> {
                    assertEquals(expectedList.mapToEntity(), resource.data)
                    assertEquals(expectedList.size, resource.data?.size)
                }

                else -> Unit
            }

        }

    }

    @Test
    @DisplayName("Should Get All Promo Success From Remote Datasource and Is Empty")
    fun testGetAllPromoSuccessEmpty() = runTest {
        val expected = emptyList<PromoDto>()

        // Arrange
        Mockito.`when`(remoteDataSource.getAllPromo())
            .thenReturn(flowOf(Resource.Success(expected)))

        // Act
        val result = fakePromoRepositoryImpl.getAllPromo()

        // Assert
        result.collect { resource ->

            when (resource) {
                is Resource.Success -> {
                    assertEquals(expected.map { it.mapToEntity() }, resource.data)
                }

                else -> Unit
            }

        }

    }

    @Test
    @DisplayName("Should Get All Promo Error From Remote Datasource and bring error")
    fun testGetAllPromoError() = runTest {
        val expected = Exception("Error").message

        // Arrange
        Mockito.`when`(remoteDataSource.getAllPromo())
            .thenReturn(flowOf(Resource.Error(expected)))

        // Act
        val result = fakePromoRepositoryImpl.getAllPromo()

        // Assert
        result.collect { resource ->

            when (resource) {
                is Resource.Error -> {
                    Assertions.assertEquals(expected, resource.message)
                }

                else -> Unit
            }

        }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    @DisplayName("Should Get All Promo Loading From Remote Datasource and bring loading")
    fun testGetAllPromoLoading() = runTest {
        val expected = true

        // Arrange
        Mockito.`when`(remoteDataSource.getAllPromo())
            .thenReturn(flowOf(Resource.Loading(expected)))

        // Act
        val result = fakePromoRepositoryImpl.getAllPromo()


        // Assert
        val job = launch {

            val collectedResources = mutableListOf<Resource<List<PromoEntity>?>>()
            val collectingJob = launch {
                result.collect { resource ->
                    collectedResources.add(resource)
                }
            }

            delay(1000L)

            assertTrue(collectedResources[0] is Resource.Loading)
            assertEquals(expected, (collectedResources[1] as Resource.Loading).isLoading)
            Mockito.verify(remoteDataSource, Mockito.times(1)).getAllPromo()

            collectingJob.cancel()

        }

        advanceTimeBy(1000L)

        job.cancel()

    }

    @Test
    @DisplayName("Should Get Promo By Id Success From Remote Datasource and Is Not Empty")
    fun testGetPromoByIdSuccess() = runTest {

        // Arrange
        Mockito.`when`(remoteDataSource.getPromoById(1))
            .thenReturn(flowOf(Resource.Success(sampleResponse)))


        // Act
        val result = fakePromoRepositoryImpl.getPromoById(1)


        // Assert
        result.collect { resource ->

            when (resource) {
                is Resource.Success -> {
                    assertEquals(sampleResponse.mapToEntity(), resource.data)
                }

                else -> Unit
            }

        }

        Mockito.verify(remoteDataSource, Mockito.times(1)).getPromoById(1)

    }

    @Test
    @DisplayName("Should Get Promo By Id Success From Remote Datasource and Is Empty")
    fun testGetPromoByIdSuccessEmpty() = runTest {

        // Arrange
        Mockito.`when`(remoteDataSource.getPromoById(1))
            .thenReturn(flowOf(Resource.Success(PromoDto())))

        // Act
        val result = fakePromoRepositoryImpl.getPromoById(1)

        // Assert
        result.collect { resource ->

            when (resource) {
                is Resource.Success -> {
                    assertEquals(PromoDto().mapToEntity(), resource.data)
                }

                else -> Unit
            }

        }

        Mockito.verify(remoteDataSource, Mockito.times(1)).getPromoById(1)
    }

    @Test
    @DisplayName("Should Get Promo By Id Error From Remote Datasource and bring error")
    fun testGetPromoByIdSuccessError() = runTest {
        val expected = Exception("Error").message

        // Arrange
        Mockito.`when`(remoteDataSource.getPromoById(1))
            .thenReturn(flowOf(Resource.Error(expected)))

        // Act
        val result = fakePromoRepositoryImpl.getPromoById(1)

        // Assert
        result.collect { resource ->

            when (resource) {
                is Resource.Error -> {
                    assertEquals(expected, resource.message)
                }

                else -> Unit
            }

        }

        Mockito.verify(remoteDataSource, Mockito.times(1)).getPromoById(1)
    }

    @Test
    @DisplayName("Should Get Promo By Id Error When Id Is Null")
    fun testGetPromoByIdErrorWhenIdIsNull() = runTest {
        val expected = "Id cannot be null"

        // Arrange
        Mockito.`when`(remoteDataSource.getPromoById(null))
            .thenReturn(flowOf(Resource.Error(expected)))

        // Act
        val result = fakePromoRepositoryImpl.getPromoById(null)

        // Assert
        result.collect { resource ->

            when (resource) {
                is Resource.Error -> {
                    assertEquals(expected, resource.message)
                }

                else -> Unit
            }

        }

        Mockito.verify(remoteDataSource, Mockito.times(1)).getPromoById(null)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    @DisplayName("Should Get Promo By Id Loading From Remote Datasource and bring loading")
    fun testGetPromoByIdLoading() = runTest {
        val expected = true

        // Arrange
        Mockito.`when`(remoteDataSource.getPromoById(1))
            .thenReturn(flowOf(Resource.Loading(expected)))

        // Act
        val result = fakePromoRepositoryImpl.getPromoById(1)

        // Assert
        val job = launch {

            val collectedResources = mutableListOf<Resource<PromoEntity?>>()
            val collectingJob = launch {
                result.collect { resource ->
                    collectedResources.add(resource)
                }
            }

            delay(1000L)

            assertTrue(collectedResources[0] is Resource.Loading)
            assertEquals(expected, (collectedResources[1] as Resource.Loading).isLoading)
            Mockito.verify(remoteDataSource, Mockito.times(1)).getPromoById(1)

            collectingJob.cancel()

        }

        advanceTimeBy(1000L)

        job.cancel()
    }


    val sampleResponse = PromoDto(
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

        )

    val expectedList = listOf<PromoDto>(
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

            )
    )


}