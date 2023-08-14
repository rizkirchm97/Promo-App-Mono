package com.rizkir.promoapp.domain.entities

data class Img(
    val id: Int?,
    val name: String?,
    val alternativeText: String?,
    val caption: String?,
    val width: Int?,
    val height: Int?,
    val formats: Formats?,
    val hash: String?,
    val ext: String?,
    val mime: String?,
    val size: Double?,
    val url: String?,
    val previewUrl: String?,
    val provider: String?,
    val provider_metadata: String?,
    val created_at: String?,
    val updated_at: String?
)
