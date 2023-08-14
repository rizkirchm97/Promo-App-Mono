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
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MapToEntityTest {


    @Test
    @DisplayName("Should Success when data mapped to entity")
    fun testMapDataSuccess() {
        val promoEntity = PromoEntity(
            8,
            Img(
                3,
                "dsaf",
                "dwqfwq",
                "dqw",
                1,
                1,
                Formats(
                    Thumbnail(
                        "dqw",
                        "dqw",
                        "dqw",
                        "dqw",
                        "dqw",
                        "dqw",
                        "dqw",
                        12,
                        12
                    ),
                    Medium(
                        "dqw",
                        "dqw",
                        "dqw",
                        "dqw",
                        "dqw",
                        "dqw",
                        "dqw",
                        12,
                        12
                    ),
                    Small(
                        "dqw",
                        "dqw",
                        "dqw",
                        "dqw",
                        "dqw",
                        "dqw",
                        "dqw",
                        12,
                        12
                    )
                ),
                "dqw",
                "dqw",
                "dqw",
                6.465,
                "Dwqdwq",
                "dqw",
                "dqw",
                "dwqd",
                "dqw",
                "dqw",
            ),
            "dw",
            "4324",
            "143",
            "532",
            "fcqwwe",
            "grega",
            "cqrq",
            "rwqcrqtq",
            "1.0",
            "1.0",
            "gewg",
            "4234325",
            "rrqwrqw",
            9
        )

        val promoDto = PromoDto(
            id = 8,
            img = ImgDto(
                id = 3,
                formats = FormatsDto(
                    thumbnail = ThumbnailDto(
                        "dqw",
                        "dqw",
                        "dqw",
                        "dqw",
                        "dqw",
                        hash = "dqw",
                        url = "dqw",
                        width = 12,
                        height = 12
                    ),
                    medium = MediumDto(
                        "dqw",
                        "dqw",
                        "dqw",
                        "dqw",
                        "dqw",
                        hash = "dqw",
                        url = "dqw",
                        width = 12,
                        height = 12
                    ),
                    small = SmallDto(
                        "dqw",
                        "dqw",
                        "dqw",
                        "dqw",
                        "dqw",
                        hash = "dqw",
                        url = "dqw",
                        width = 12,
                        height = 12
                    )
                ),
                url = "dqw",
                width = 1,
                height = 1,
                hash = "dsaf",
                ext = "dwqfwq",
                mime = "dqw",
                size = 2.02,
                previewUrl = "dqw",
                alternativeText = "dwqfwq",
                provider = "dqw",
                providerMetadata = "dqw",
                createdAt = "dqw",
                updatedAt = "dqw"

            ),
            title = "dw",
            desc = "rwqcrqtq",
            descPromo = "grega",
            lokasi = "rrqwrqw",
            namePromo = "fcqwwe",
            nama = "cqrq",
            alt = "gewg",
            latitude = "1.0",
            longitude = "1.0",
            count = 9,
            createdAt = "4234325",
            created_At = "143",
            updatedAt = "532",
            publishedAt = "4324",

            )


        assertEquals(promoDto.mapToEntity(), promoDto.mapToEntity())
        assertEquals(promoDto.img?.mapToEntity(), promoDto.img?.mapToEntity())
        assertEquals(promoDto.img?.formats?.mapToEntity(), promoDto.img?.formats?.mapToEntity())
        assertEquals(promoDto.img?.formats?.thumbnail?.mapToEntity(),promoDto.img?.formats?.thumbnail?.mapToEntity())
        assertEquals(promoDto.img?.formats?.medium?.mapToEntity(), promoDto.img?.formats?.medium?.mapToEntity())
        assertEquals(promoDto.img?.formats?.small?.mapToEntity(), promoDto.img?.formats?.small?.mapToEntity())
    }



}

