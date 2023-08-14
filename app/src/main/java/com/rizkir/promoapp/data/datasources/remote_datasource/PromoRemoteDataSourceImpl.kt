package com.rizkir.promoapp.data.datasources.remote_datasource

import com.rizkir.promoapp.common.Resource
import com.rizkir.promoapp.data.datasources.remote_datasource.dto.PromoDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PromoRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
): PromoRemoteDataSource {
    override suspend fun getAllPromo(): Flow<Resource<List<PromoDto>>> = flow {
        val result = apiService.getAllPromo()
        try {
            if (result.isSuccessful) {
                emit(Resource.Success(result.body() ?: emptyList()))
            } else {
                emit(Resource.Error(result.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }

    }

    override fun getPromoById(i: Int?): Flow<Resource<PromoDto>> = flow {
        if ( i == null) {
            emit(Resource.Error("id not found"))
        } else {
            val result = apiService.getAllPromo()
            try {
                if (result.isSuccessful) {
                    val data = result.body()?.find { it.id == i }
                    emit(Resource.Success(data ?: PromoDto()))
                } else {
                    emit(Resource.Error(result.message()))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }
}