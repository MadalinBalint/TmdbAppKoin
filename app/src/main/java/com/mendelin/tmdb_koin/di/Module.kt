package com.mendelin.tmdb_koin.di

import com.mendelin.tmdb_koin.data.api.RetrofitServiceProvider
import com.mendelin.tmdb_koin.data.repository.local.PreferencesRepository
import com.mendelin.tmdb_koin.data.repository.remote.MoviesRepository
import com.mendelin.tmdb_koin.data.repository.remote.PeopleRepository
import com.mendelin.tmdb_koin.data.repository.remote.TvShowsRepository
import com.mendelin.tmdb_koin.ui.home.HomeViewModel
import com.mendelin.tmdb_koin.ui.movie_details.MoviesDetailsViewModel
import com.mendelin.tmdb_koin.ui.movies_now_playing.MoviesNowPlayingViewModel
import com.mendelin.tmdb_koin.ui.movies_popular.MoviesPopularViewModel
import com.mendelin.tmdb_koin.ui.movies_upcoming.MoviesUpcomingViewModel
import com.mendelin.tmdb_koin.ui.person.PersonViewModel
import com.mendelin.tmdb_koin.ui.tv_show_season_episodes.TvShowSeasonEpisodesViewModel
import com.mendelin.tmdb_koin.ui.tv_show_seasons.TvShowSeasonsViewModel
import com.mendelin.tmdb_koin.ui.tv_shows_on_the_air.TvOnTheAirViewModel
import com.mendelin.tmdb_koin.ui.tv_shows_popular.TvPopularViewModel
import com.mendelin.tmdb_koin.ui.tv_shows_top_rated.TvTopRatedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleTmdb = module {
    viewModel { HomeViewModel(get(), get()) }

    viewModel { MoviesNowPlayingViewModel(get(), get()) }

    viewModel { MoviesPopularViewModel(get(), get()) }

    viewModel { MoviesUpcomingViewModel(get(), get()) }

    viewModel { TvOnTheAirViewModel(get(), get()) }

    viewModel { TvPopularViewModel(get(), get()) }

    viewModel { TvTopRatedViewModel(get(), get()) }

    viewModel { MoviesDetailsViewModel(get()) }

    viewModel { TvShowSeasonsViewModel(get()) }

    viewModel { TvShowSeasonEpisodesViewModel(get()) }

    viewModel { PersonViewModel(get()) }

    single { MoviesRepository(get()) }

    single { TvShowsRepository(get()) }

    single { PeopleRepository(get()) }

    single { PreferencesRepository(get()) }
}

val moduleNetwork = module {
    single { RetrofitServiceProvider.getService() }
}