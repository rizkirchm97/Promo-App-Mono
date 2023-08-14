package com.rizkir.promoapp.data.mapper

import com.rizkir.promoapp.data.datasources.remote_datasource.dto.FormatsDto
import com.rizkir.promoapp.data.datasources.remote_datasource.dto.ImgDto
import com.rizkir.promoapp.data.datasources.remote_datasource.dto.MediumDto
import com.rizkir.promoapp.data.datasources.remote_datasource.dto.PromoDto
import com.rizkir.promoapp.data.datasources.remote_datasource.dto.SmallDto
import com.rizkir.promoapp.data.datasources.remote_datasource.dto.ThumbnailDto
import com.rizkir.promoapp.domain.entities.Formats
import com.rizkir.promoapp.domain.entities.Img
import com.rizkir.promoapp.domain.entities.Medium
import com.rizkir.promoapp.domain.entities.PromoEntity
import com.rizkir.promoapp.domain.entities.Small
import com.rizkir.promoapp.domain.entities.Thumbnail

fun PromoDto.mapToEntity(): PromoEntity {
    return PromoEntity(
        id = id,
        img = img?.mapToEntity(),
        title = title,
        desc = desc,
        descPromo = descPromo,
        lokasi = lokasi,
        namePromo = namePromo,
        nama = nama,
        alt = alt,
        latitude = latitude,
        longitude = longitude,
        count = count,
        createdAt = createdAt,
        created_at = created_At,
        updated_at = updatedAt,
        published_at = publishedAt,
    )
}

fun ImgDto.mapToEntity(): Img {
    return Img(
        id = id,
        name = name,
        alternativeText = alternativeText,
        caption = caption,
        width = width,
        height = height,
        formats = formats?.mapToEntity(),
        hash = hash,
        ext = ext,
        mime = mime,
        size = size,
        url = url,
        previewUrl = previewUrl,
        provider = provider,
        provider_metadata = providerMetadata,
        created_at = createdAt,
        updated_at = updatedAt,
    )
}

fun FormatsDto.mapToEntity(): Formats {
    return Formats(
        thumbnail = thumbnail?.mapToEntity(),
        medium = medium?.mapToEntity(),
        small = small?.mapToEntity(),
    )
}

fun ThumbnailDto.mapToEntity(): Thumbnail {
    return Thumbnail(
        name = name,
        hash = hash,
        ext = ext,
        mime = mime,
        width = width,
        height = height,
        size = size,
        url = url,
        path = path,
    )
}

fun MediumDto.mapToEntity(): Medium {
    return Medium(
        name = name,
        hash = hash,
        ext = ext,
        mime = mime,
        width = width,
        height = height,
        size = size,
        url = url,
        path = path,
    )
}

fun SmallDto.mapToEntity(): Small {
    return Small(
        name = name,
        hash = hash,
        ext = ext,
        mime = mime,
        width = width,
        height = height,
        size = size,
        url = url,
        path = path,
    )
}

fun List<PromoDto>.mapToEntity(): List<PromoEntity> {
    return map { it.mapToEntity() }
}

