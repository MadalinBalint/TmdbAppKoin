package com.mendelin.tmdb_koin.data.model.entity

import androidx.annotation.Keep

@Keep
data class SeasonItem(
    val air_date: String,
    val episode_count: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String,
    val season_number: Int
)