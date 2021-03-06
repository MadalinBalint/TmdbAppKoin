package com.mendelin.tmdb_koin.data.repository.remote

import com.mendelin.tmdb_koin.data.api.TmdbDataSource
import com.mendelin.tmdb_koin.data.model.response.CreditsResponse
import com.mendelin.tmdb_koin.data.model.entity.MovieListResultItem
import com.mendelin.tmdb_koin.data.model.response.MovieDetailsResponse
import com.mendelin.tmdb_koin.data.model.response.NowPlayingGenericResponse
import com.mendelin.tmdb_koin.data.model.response.PagedGenericResponse
import retrofit2.Response

class MoviesRepository(private val service: TmdbDataSource) {
    suspend fun getMovieDetails(movie_id: Int): Response<MovieDetailsResponse> =
        service.getMovieDetails(movie_id)

    suspend fun getMovieCredits(movie_id: Int): Response<CreditsResponse> =
        service.getMovieCredits(movie_id)

    suspend fun getNowPlayingMovies(page: Int): Response<NowPlayingGenericResponse<MovieListResultItem>> =
        service.getMovieNowPlaying(page = page)

    suspend fun getPopularMovies(page: Int): Response<PagedGenericResponse<MovieListResultItem>> =
        service.getMoviePopular(page = page)

    suspend fun getTopRatedMovies(page: Int): Response<PagedGenericResponse<MovieListResultItem>> =
        service.getMoviesTopRated(page = page)

    suspend fun getUpcomingMovies(page: Int): Response<NowPlayingGenericResponse<MovieListResultItem>> =
        service.getMovieUpcoming(page = page)
}