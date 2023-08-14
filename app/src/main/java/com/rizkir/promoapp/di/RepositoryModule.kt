package com.rizkir.promoapp.di

import com.rizkir.promoapp.data.repositories.PromoRepositoryImpl
import com.rizkir.promoapp.domain.repositories.PromoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindPromoRepository(
        promoRepositoryImpl: PromoRepositoryImpl
    ): PromoRepository

}