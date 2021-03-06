package com.mendelin.tmdb_koin.data.model.entity

import androidx.annotation.Keep

@Keep
data class EpisodeCrewItem(
    val id: Int,
    val credit_id: String,
    val name: String,
    val department: String,
    val job: String,
    val profile_path: String?
)