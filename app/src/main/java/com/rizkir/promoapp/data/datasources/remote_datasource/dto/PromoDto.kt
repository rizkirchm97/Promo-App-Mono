package com.rizkir.promoapp.data.datasources.remote_datasource.dto

import com.squareup.moshi.Json

data class PromoDto(

	@field:Json(name="img")
	val img: ImgDto? = null,

	@field:Json(name="latitude")
	val latitude: String? = null,

	@field:Json(name="alt")
	val alt: String? = null,

	@field:Json(name="count")
	val count: Int? = null,

	@field:Json(name="Title")
	val title: String? = null,

	@field:Json(name="created_at")
	val created_At: String? = null,

	@field:Json(name="createdAt")
	val createdAt: String? = null,

	@field:Json(name="updated_at")
	val updatedAt: String? = null,

	@field:Json(name="nama")
	val nama: String? = null,

	@field:Json(name="name_promo")
	val namePromo: String? = null,

	@field:Json(name="lokasi")
	val lokasi: String? = null,

	@field:Json(name="id")
	val id: Int? = null,

	@field:Json(name="published_at")
	val publishedAt: String? = null,

	@field:Json(name="desc_promo")
	val descPromo: String? = null,

	@field:Json(name="desc")
	val desc: String? = null,

	@field:Json(name="longitude")
	val longitude: String? = null
)
