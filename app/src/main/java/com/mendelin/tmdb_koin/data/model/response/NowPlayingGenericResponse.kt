package com.mendelin.tmdb_koin.data.model.response

import androidx.annotation.Keep
import com.mendelin.tmdb_koin.data.model.entity.DatesItem

@Keep
data class NowPlayingGenericResponse<T>(
    val page: Int,
    val results: List<T> = emptyList(),
    val dates: DatesItem,
    val total_results: Int = 0,
    val total_pages: Int = 0,
    val status_message: String = "",
    val status_code: Int = 200
)