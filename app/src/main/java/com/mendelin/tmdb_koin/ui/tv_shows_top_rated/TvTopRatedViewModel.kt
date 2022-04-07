package com.mendelin.tmdb_koin.ui.tv_shows_top_rated

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.mendelin.tmdb_koin.base.BaseViewModel
import com.mendelin.tmdb_koin.data.repository.local.PreferencesRepository
import com.mendelin.tmdb_koin.data.repository.remote.TvShowsRepository

class TvTopRatedViewModel(private val repo: TvShowsRepository, private val preferences: PreferencesRepository) : BaseViewModel() {
    val topRatedTvShows = Pager(
        config = PagingConfig(pageSize = PreferencesRepository.ITEMS_PER_PAGE),
        pagingSourceFactory = {
            TvTopRatedPagingSource(repo, preferences)
        }
    ).flow.cachedIn(viewModelScope)
}