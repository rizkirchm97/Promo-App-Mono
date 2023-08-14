package com.rizkir.promoapp.data.datasources.remote_datasource.dto

import com.squareup.moshi.Json

data class MediumDto(
    @field:Json(name="ext")
    val ext: String? = null,

    @field:Json(name="path")
    val path: String? = null,

    @field:Json(name="size")
    val size: String? = null,

    @field:Json(name="mime")
    val mime: String? = null,

    @field:Json(name="name")
    val name: String? = null,

    @field:Json(name="width")
    val width: Int? = null,

    @field:Json(name="url")
    val url: String? = null,

    @field:Json(name="hash")
    val hash: String? = null,

    @field:Json(name="height")
    val height: Int? = null
)
