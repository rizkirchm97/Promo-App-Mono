package com.rizkir.promoapp.domain.usecases

import com.rizkir.promoapp.domain.repositories.PromoRepository
import javax.inject.Inject


class GetAllPromoUseCase @Inject constructor(
    private val promoRepository: PromoRepository
)  {
    suspend operator fun invoke() = promoRepository.getAllPromo()
}
