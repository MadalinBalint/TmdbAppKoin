package com.mendelin.tmdb_koin.data.model.response

import androidx.annotation.Keep
import com.mendelin.tmdb_koin.data.model.entity.TvCreditsCastItem
import com.mendelin.tmdb_koin.data.model.entity.TvCreditsCrewItem

@Keep
data class PersonTvCreditsResponse(
    val cast: List<TvCreditsCastItem>,
    val crew: List<TvCreditsCrewItem>,
    val id: Int
)