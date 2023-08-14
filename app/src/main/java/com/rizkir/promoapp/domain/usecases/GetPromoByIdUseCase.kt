package com.rizkir.promoapp.domain.usecases

import com.rizkir.promoapp.domain.repositories.PromoRepository
import javax.inject.Inject

class GetPromoByIdUseCase @Inject constructor (private val promoRepository: PromoRepository)  {
    suspend operator fun invoke(id: Int?) = promoRepository.getPromoById(id)
}