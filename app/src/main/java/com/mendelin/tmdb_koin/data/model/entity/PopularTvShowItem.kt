package com.mendelin.tmdb_koin.data.model.entity

import androidx.annotation.Keep
import java.text.SimpleDateFormat
import java.util.*

@Keep
data class TvListResultItem(
    val poster_path: String?,
    val popularity: Float,
    val id: Int,
    val backdrop_path: String?,
    val vote_average: Float,
    val overview: String,
    val first_air_date: String,
    val origin_country: List<String>,
    val genre_ids: List<Int>,
    val original_language: String,
    val vote_count: Int,
    val name: String,
    val original_name: String
) {
    fun getYear(): String {
        val sd = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = sd.parse(first_air_date)
        val calendar = Calendar.getInstance()
        calendar.time = date!!
        return calendar.get(Calendar.YEAR).toString()
    }
}