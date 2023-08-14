package com.rizkir.promoapp.data.datasources.remote_datasource

import com.rizkir.promoapp.data.datasources.remote_datasource.dto.PromoDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/promos")
    suspend fun getAllPromo(): Response<List<PromoDto>>
}