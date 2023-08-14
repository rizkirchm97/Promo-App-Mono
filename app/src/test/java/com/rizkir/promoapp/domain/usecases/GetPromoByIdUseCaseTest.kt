package com.rizkir.promoapp.domain.usecases

import com.rizkir.promoapp.common.Resource
import com.rizkir.promoapp.domain.entities.Img
import com.rizkir.promoapp.domain.entities.PromoEntity
import com.rizkir.promoapp.domain.repositories.PromoRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@Extensions(
    value = [
        ExtendWith(MockitoExtension::class)
    ])
class GetPromoByIdUseCaseTest {

    @Mock
    lateinit var promoRepository: PromoRepository

    private lateinit var getPromoByIdUseCase: GetPromoByIdUseCase

    @BeforeEach
    fun beforeEach() {
        getPromoByIdUseCase = GetPromoByIdUseCase(promoRepository)
    }

    @Test
    @DisplayName("Should Get Success From repository")
    fun testGetPromoByIdSuccess() = runTest {
        // Arrange
        Mockito.`when`(promoRepository.getPromoById(1)).thenReturn(flowOf(Resource.Success(sampleResponse)))

        // Act
        val act = getPromoByIdUseCase.invoke(1)

        // Assert
        assertEquals(sampleResponse, act.first().data)
        assertNotNull(act.first().data)
        Mockito.verify(promoRepository).getPromoById(1)


    }

    @Test
    @DisplayName("Should Get Error From repository")
    fun testGetPromoByIdError() = runTest {
        // Arrange
        Mockito.`when`(promoRepository.getPromoById(1)).thenReturn(flowOf(Resource.Error("Error", null)) )

        // Act
        val act = getPromoByIdUseCase.invoke(1)

        // Assert
        assertNull(act.first().data)
        assertEquals("Error", act.first().message)
        Mockito.verify(promoRepository).getPromoById(1)
    }

    @Test
    @DisplayName("Should Get Loading From repository")
    fun testGetPromoByIdLoading() = runTest {
        // Arrange
        Mockito.`when`(promoRepository.getPromoById(1)).thenReturn(flowOf(Resource.Loading(true)) )

        // Act
        val act = getPromoByIdUseCase.invoke(1).first()

        // Assert
        assertInstanceOf(Resource.Loading::class.java, act)
        if (act is Resource.Loading) {
            assertTrue(act.isLoading)
            assertNotNull(act.isLoading)
        }

        Mockito.verify(promoRepository).getPromoById(1)
    }


    val sampleResponse = PromoEntity(
        7,
        Img(
            3,
            "dsaf",
            "dwqfwq",
            "dqw",
            1,
            1,
            null,
            "dqw",
            "dqw",
            "dqw",
            6.465,
            "Dwqdwq",
            "dqw",
            "dqw",
            "dwqd",
            "dqw",
            "dqw",
        ),
        "dqd",
        "321",
        "421",
        "141",
        "fdsfds",
        "vdsfs",
        "1412",
        "fafa",
        "1.0",
        "1.0",
        "1",
        "4314",
        "ffa a",
        5
    )

}