package com.rizkir.promoapp.data.repositoryimpl

import com.rizkir.promoapp.common.Resource
import com.rizkir.promoapp.data.datasources.remote_datasource.PromoRemoteDataSource
import com.rizkir.promoapp.data.mapper.mapToEntity
import com.rizkir.promoapp.domain.entities.PromoEntity
import com.rizkir.promoapp.domain.repositories.PromoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class FakeRepositoryImpl(
    private val remoteDataSource: PromoRemoteDataSource
) : PromoRepository {
    override suspend fun getAllPromo(): Flow<Resource<List<PromoEntity>?>> = flow {

        emit(Resource.Loading())
        val resultData = remoteDataSource.getAllPromo()

        resultData
            .map {
                it.data?.map { data ->
                    data.mapToEntity()
                        .also { data.img?.mapToEntity() }
                        .also { data.img?.formats?.mapToEntity() }
                        .also { data.img?.formats?.thumbnail?.mapToEntity() }
                        .also { data.img?.formats?.medium?.mapToEntity() }
                        .also { data.img?.formats?.small?.mapToEntity() }
                }
            }
            .catch { err ->
                emit(Resource.Error(err.message.toString()))
            }
            .collect { resource ->
                emit(Resource.Success(resource))
                emit(Resource.Loading(false))
            }
    }

    override suspend fun getPromoById(id: Int?): Flow<Resource<PromoEntity?>> = flow {

        emit(Resource.Loading())
        val resultData = remoteDataSource.getPromoById(id)

        resultData
            .catch { err ->
                emit(Resource.Error(err.message.toString())) }
            .map {data ->
                data.data?.mapToEntity()
                    .also { data.data?.img?.mapToEntity() }
                    .also { data.data?.img?.formats?.mapToEntity() }
                    .also { data.data?.img?.formats?.thumbnail?.mapToEntity() }
                    .also { data.data?.img?.formats?.medium?.mapToEntity() }
                    .also { data.data?.img?.formats?.small?.mapToEntity() }
            }
            .catch { err ->
                emit(Resource.Error(err.message.toString()))
            }
            .collect {
                emit(Resource.Success(it))
                emit(Resource.Loading(false))
            }
    }
}