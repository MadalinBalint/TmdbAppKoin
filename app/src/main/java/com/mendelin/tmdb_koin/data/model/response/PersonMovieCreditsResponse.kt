package com.mendelin.tmdb_koin.data.model.response

import androidx.annotation.Keep
import com.mendelin.tmdb_koin.data.model.entity.MovieCreditsCastItem
import com.mendelin.tmdb_koin.data.model.entity.MovieCreditsCrewItem

@Keep
data class PersonMovieCreditsResponse(
    val cast: List<MovieCreditsCastItem>,
    val crew: List<MovieCreditsCrewItem>,
    val id: Int
)