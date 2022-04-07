package com.mendelin.tmdb_koin.ui.movies_now_playing

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.mendelin.tmdb_koin.base.BaseViewModel
import com.mendelin.tmdb_koin.data.repository.local.PreferencesRepository
import com.mendelin.tmdb_koin.data.repository.remote.MoviesRepository

class MoviesNowPlayingViewModel(private val repo: MoviesRepository, private val preferences: PreferencesRepository) : BaseViewModel() {
    val nowPlayingMovies = Pager(
        config = PagingConfig(pageSize = PreferencesRepository.ITEMS_PER_PAGE),
        pagingSourceFactory = {
            MoviesNowPlayingPagingSource(repo, preferences)
        }
    ).flow.cachedIn(viewModelScope)
}