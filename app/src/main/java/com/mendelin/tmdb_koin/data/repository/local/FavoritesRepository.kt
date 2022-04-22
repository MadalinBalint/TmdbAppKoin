package com.mendelin.tmdb_koin.data.repository.local

import com.mendelin.tmdb_koin.data.model.entity.MovieListResultItem
import com.mendelin.tmdb_koin.data.model.entity.TvListResultItem
import com.mendelin.tmdb_koin.data.room.FavoritesDao
import kotlinx.coroutines.flow.Flow

class FavoritesRepository(private val dataSource: FavoritesDao) {
    /* Movies */
    suspend fun insertFavoriteMovie(movie: MovieListResultItem) {
        dataSource.insertFavoriteMovie(movie)
    }

    suspend fun isFavoriteMovie(id: Int): MovieListResultItem? =
        dataSource.isFavoriteMovie(id)

    suspend fun deleteFavoriteMovie(id: Int) =
        dataSource.deleteFavoriteMovie(id)

    fun getFavoriteMovies(): Flow<List<MovieListResultItem>> =
        dataSource.getFavoriteMovies()

    /* TV Shows */
    suspend fun insertFavoriteTvShow(tvShow: TvListResultItem) {
        dataSource.insertFavoriteTvShow(tvShow)
    }

    suspend fun isFavoriteTvShow(id: Int): TvListResultItem? =
        dataSource.isFavoriteTvShow(id)

    suspend fun deleteFavoriteTvShow(id: Int) =
        dataSource.deleteFavoriteTvShow(id)

    fun getFavoriteTvShows(): Flow<List<TvListResultItem>> =
        dataSource.getFavoriteTvShows()
}