package com.rizkir.promoapp.features.detail_promo

sealed class DetailPromoEvent() {
    data class FetchPromoDetail(val promoId: Int): DetailPromoEvent()
}
