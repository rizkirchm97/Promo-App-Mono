package com.rizkir.promoapp.data.datasources.remote_datasource.dto

import com.squareup.moshi.Json

data class ImgDto(

    @field:Json(name = "ext")
    val ext: String? = null,

    @field:Json(name = "formats")
    val formats: FormatsDto? = null,

    @field:Json(name = "previewUrl")
    val previewUrl: String? = null,

    @field:Json(name = "mime")
    val mime: String? = null,

    @field:Json(name = "caption")
    val caption: String? = null,

    @field:Json(name = "created_at")
    val createdAt: String? = null,

    @field:Json(name = "url")
    val url: String? = null,

    @field:Json(name = "size")
    val size: Double? = null,

    @field:Json(name = "updated_at")
    val updatedAt: String? = null,

    @field:Json(name = "provider")
    val provider: String? = null,

    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "width")
    val width: Int? = null,

    @field:Json(name = "provider_metadata")
    val providerMetadata: String? = null,

    @field:Json(name = "id")
    val id: Int? = null,

    @field:Json(name = "alternativeText")
    val alternativeText: String? = null,

    @field:Json(name = "hash")
    val hash: String? = null,

    @field:Json(name = "height")
    val height: Int? = null

)
