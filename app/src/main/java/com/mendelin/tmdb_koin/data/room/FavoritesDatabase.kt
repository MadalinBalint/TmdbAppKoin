package com.mendelin.tmdb_koin.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mendelin.tmdb_koin.data.model.entity.MovieListResultItem
import com.mendelin.tmdb_koin.data.model.entity.TvListResultItem


@Database(entities = [MovieListResultItem::class, TvListResultItem::class], version = 2)
@TypeConverters(ConverterIntList::class, ConverterStringList::class)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao

    companion object {
        private var INSTANCE: FavoritesDatabase? = null

        fun getDatabase(context: Context): FavoritesDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    FavoritesDatabase::class.java, "FavoritesDB"
                ).build()
            }
            return INSTANCE!!
        }
    }
}