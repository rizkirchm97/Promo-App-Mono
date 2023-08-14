package com.rizkir.promoapp.di

import com.rizkir.promoapp.data.repositories.PromoRepositoryImpl
import com.rizkir.promoapp.domain.repositories.PromoRepository
import com.rizkir.promoapp.domain.usecases.GetAllPromoUseCase
import com.rizkir.promoapp.domain.usecases.GetPromoByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetAllPromoUseCase(
        promoRepository: PromoRepository
    ) = GetAllPromoUseCase(promoRepository)

    @Provides
    @Singleton
    fun provideGetPromoByIdUseCase(
        promoRepository: PromoRepository
    ) = GetPromoByIdUseCase(promoRepository)

}