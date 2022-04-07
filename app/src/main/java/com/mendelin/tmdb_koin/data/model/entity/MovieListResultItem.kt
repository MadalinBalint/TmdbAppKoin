package com.mendelin.tmdb_koin.data.model.entity

import androidx.annotation.Keep
import java.text.SimpleDateFormat
import java.util.*

@Keep
data class MovieListResultItem(
    val poster_path: String?,
    val adult: Boolean,
    val overview: String,
    val release_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_title: String,
    val original_language: String,
    val title: String,
    val backdrop_path: String?,
    val popularity: Float,
    val vote_count: Int,
    val video: Boolean,
    val vote_average: Float
) {
    fun getYear(): String {
        val sd = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = sd.parse(release_date)
        val calendar = Calendar.getInstance()
        calendar.time = date!!
        return calendar.get(Calendar.YEAR).toString()
    }
}