package com.rizkir.promoapp.data.datasources.remote_datasource

import com.rizkir.promoapp.common.Resource
import com.rizkir.promoapp.data.datasources.remote_datasource.dto.PromoDto
import kotlinx.coroutines.flow.Flow

interface PromoRemoteDataSource {
    suspend fun getAllPromo(): Flow<Resource<List<PromoDto>>>
    fun getPromoById(i: Int?): Flow<Resource<PromoDto>>
}