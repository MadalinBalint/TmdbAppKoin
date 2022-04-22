package com.mendelin.tmdb_koin.ui.favorites

import androidx.lifecycle.MutableLiveData
import com.mendelin.tmdb_koin.base.BaseViewModel
import com.mendelin.tmdb_koin.common.FavoriteType
import com.mendelin.tmdb_koin.data.model.entity.MovieListResultItem
import com.mendelin.tmdb_koin.data.model.entity.MultipleItem
import com.mendelin.tmdb_koin.data.model.entity.TvListResultItem
import com.mendelin.tmdb_koin.data.repository.local.FavoritesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber

class FavoritesViewModel(private val repo: FavoritesRepository) : BaseViewModel() {
    val favoritesList = MutableLiveData<List<MultipleItem>>()

    private suspend fun fetchFavoriteMovies(): List<MultipleItem> {
        return repo.getFavoriteMovies()
            .first()
            .map {
                MultipleItem(FavoriteType.FAVORITE_MOVIE, it)
            }
    }

    private suspend fun fetchFavoriteTvShows(): List<MultipleItem> {
        return repo.getFavoriteTvShows()
            .first()
            .map {
                MultipleItem(FavoriteType.FAVORITE_TV_SHOW, it)
            }
    }

    fun fetchFavoritesList() {
        CoroutineScope(Dispatchers.IO).launch {
            val favorites = mutableListOf<MultipleItem>()
            val movies = fetchFavoriteMovies()
            val tvShows = fetchFavoriteTvShows()

            Timber.d("We have ${movies.size} favorite movies")
            Timber.d("We have ${tvShows.size} favorite TV shows")

            favorites.addAll(movies)
            favorites.addAll(tvShows)

            favoritesList.postValue(favorites)
        }
    }

    fun isFavoriteMovie(id: Int): Boolean {
        var favorite: Boolean
        runBlocking(Dispatchers.IO) {
            favorite = repo.isFavoriteMovie(id) != null
        }
        return favorite
    }

    fun isFavoriteTvShow(id: Int): Boolean {
        var favorite: Boolean
        runBlocking(Dispatchers.IO) {
            favorite = repo.isFavoriteTvShow(id) != null
        }
        return favorite
    }

    suspend fun insertFavoriteMovie(movie: MovieListResultItem) {
        repo.insertFavoriteMovie(movie)
    }

    suspend fun insertFavoriteTvShow(tvShow: TvListResultItem) {
        repo.insertFavoriteTvShow(tvShow)
    }

    suspend fun deleteFavoriteMovie(id: Int) {
        repo.deleteFavoriteMovie(id)
    }

    suspend fun deleteFavoriteTvShow(id: Int) {
        repo.deleteFavoriteTvShow(id)
    }
}