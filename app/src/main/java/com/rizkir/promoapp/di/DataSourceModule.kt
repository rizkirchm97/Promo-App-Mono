package com.rizkir.promoapp.di

import com.rizkir.promoapp.data.datasources.remote_datasource.PromoRemoteDataSource
import com.rizkir.promoapp.data.datasources.remote_datasource.PromoRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindRemoteDataSource(
        promoRemoteDataSourceImpl: PromoRemoteDataSourceImpl
    ): PromoRemoteDataSource

}