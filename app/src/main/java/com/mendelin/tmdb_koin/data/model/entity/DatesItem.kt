package com.mendelin.tmdb_koin.data.model.entity

import androidx.annotation.Keep
import java.util.*

@Keep
data class DatesItem(
    val maximum: Date,
    val minimum: Date
)