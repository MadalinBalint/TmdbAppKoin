package com.mendelin.tmdb_koin.data.room

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromList(list: List<Int>): String = list.joinToString(",")

    @TypeConverter
    fun toList(s: String): List<Int> {
        return s.split(",")
            .map { it.toInt() }
    }
}