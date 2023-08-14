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
    ]
)
class GetAllPromoUseCaseTest {

    @Mock
    lateinit var promoRepository: PromoRepository

    private lateinit var getAllPromoUseCase: GetAllPromoUseCase

    @BeforeEach
    fun beforeEach() {
        getAllPromoUseCase = GetAllPromoUseCase(promoRepository)
    }

    @Test
    @DisplayName("Should Get Success From repository")
    fun testGetPromoSuccess() = runTest {

        val expected = listOf<PromoEntity>(
            PromoEntity(
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
            ),
            PromoEntity(
                8,
                img = null,
                "dw",
                "4324",
                "143",
                "532",
                "fcqwwe",
                "grega",
                "cqrq",
                "rwqcrqtq",
                "1.0",
                "1.0",
                "gewg",
                "4234325",
                "rrqwrqw",
                9
            )
        )


        // Arrange
        val repo = promoRepository.getAllPromo()
        Mockito.`when`(repo).thenReturn(flowOf(Resource.Success(expected)))

        // Act
        val actual = getAllPromoUseCase.invoke()

        // Assert
        assertEquals(Resource.Success(expected).data, actual.first().data)
        Mockito.verify(promoRepository, Mockito.times(1)).getAllPromo()

    }


    @Test
    @DisplayName("Should Get Empty From repository")
    fun testGetPromoIsEmpty() = runTest {

        val expected = listOf<PromoEntity>()

        // Arrange
        val repo = promoRepository.getAllPromo()
        Mockito.`when`(repo).thenReturn(flowOf(Resource.Success(emptyList())))


        // Act
        val actual = getAllPromoUseCase.invoke()

        // Assert
        assertEquals(Resource.Success(expected).data, actual.first().data)
        Mockito.verify(promoRepository, Mockito.times(1)).getAllPromo()

    }


    @Test
    @DisplayName("Should Get Error From repository")
    fun testGetPromoError() = runTest {

        val expected = "Error"

        // Arrange
        val repo = promoRepository.getAllPromo()
        Mockito.`when`(repo).thenReturn(flowOf(Resource.Error(message = expected)))

        // Act
        val actual = getAllPromoUseCase.invoke()

        // Assert
        assertEquals(Resource.Error(message = expected, data = null).message, actual.first().message)
        Mockito.verify(promoRepository, Mockito.times(1)).getAllPromo()

    }




}