package com.rizkir.promoapp.data.datasources.remote_datasource.dto

import com.squareup.moshi.Json

data class FormatsDto(
    @field:Json(name="small")
    val small: SmallDto? = null,

    @field:Json(name="thumbnail")
    val thumbnail: ThumbnailDto? = null,

    @field:Json(name="medium")
    val medium: MediumDto? = null
)
