package com.mendelin.tmdb_koin.ui.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mendelin.tmdb_koin.base.BaseViewModel
import com.mendelin.tmdb_koin.common.RetrofitResponseHandler
import com.mendelin.tmdb_koin.data.model.entity.CastItem
import com.mendelin.tmdb_koin.data.model.response.CreditsResponse
import com.mendelin.tmdb_koin.data.model.response.MovieDetailsResponse
import com.mendelin.tmdb_koin.data.repository.remote.MoviesRepository

class MoviesDetailsViewModel(private val repo: MoviesRepository) : BaseViewModel() {
    private val movieDetails = MutableLiveData<MovieDetailsResponse>()
    private val movieCredits = MutableLiveData<CreditsResponse>()
    private val movieCasting = MutableLiveData<List<CastItem>>()

    fun fetchMovieDetails(movie_id: Int) {
        isLoading.value = true
        RetrofitResponseHandler<MovieDetailsResponse>(
            { response ->
                movieDetails.postValue(response)
                isLoading.value = false
            },
            { errorMsg ->
                error.postValue(errorMsg)
                isLoading.value = false
            })
            .processData(movie_id, repo::getMovieDetails)
    }

    fun fetchMovieCredits(movie_id: Int) {
        isLoading.value = true
        RetrofitResponseHandler<CreditsResponse>(
            { response ->
                movieCredits.postValue(response)
                movieCasting.postValue(response.cast)

                isLoading.value = false
            },

            { errorMsg ->
                error.postValue(errorMsg)
                isLoading.value = false
            })
            .processData(movie_id, repo::getMovieCredits)
    }

    val details: LiveData<MovieDetailsResponse>
        get() = movieDetails

    val credits: LiveData<CreditsResponse>
        get() = movieCredits

    val casting: LiveData<List<CastItem>>
        get() = movieCasting
}