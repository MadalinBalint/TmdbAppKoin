package com.mendelin.tmdb_koin.data.model.entity

import androidx.annotation.Keep
import com.mendelin.tmdb_koin.common.FavoriteType

@Keep
data class MultipleItem(
    val type: FavoriteType,
    val content: FavoriteItem
)