package com.mendelin.tmdb_koin.data.model.response

import androidx.annotation.Keep
import com.mendelin.tmdb_koin.data.model.entity.EpisodeCrewItem
import com.mendelin.tmdb_koin.data.model.entity.GuestStarItem

@Keep
data class TvEpisodeDetailsResponse(
    val air_date: String,
    val crew: List<EpisodeCrewItem>,
    val episode_number: Int,
    val guest_stars: List<GuestStarItem>,
    val name: String,
    val overview: String,
    val id: Int,
    val production_code: String,
    val season_number: Int,
    val still_path: String,
    val vote_average: Float,
    val vote_count: Int,

    val status_message: String = "",
    val success: Boolean = true,
    val status_code: Int = 200
)