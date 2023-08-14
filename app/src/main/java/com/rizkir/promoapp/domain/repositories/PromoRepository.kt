package com.rizkir.promoapp.domain.repositories

import com.rizkir.promoapp.common.Resource
import com.rizkir.promoapp.domain.entities.PromoEntity
import kotlinx.coroutines.flow.Flow

interface PromoRepository {
    suspend fun getAllPromo(): Flow<Resource<List<PromoEntity>?>>
    suspend fun getPromoById(id: Int?): Flow<Resource<PromoEntity?>>
}
