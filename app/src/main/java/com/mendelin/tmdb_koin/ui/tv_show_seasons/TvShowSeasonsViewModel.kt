package com.mendelin.tmdb_koin.ui.tv_show_seasons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mendelin.tmdb_koin.base.BaseViewModel
import com.mendelin.tmdb_koin.common.RetrofitResponseHandler
import com.mendelin.tmdb_koin.data.model.entity.SeasonItem
import com.mendelin.tmdb_koin.data.model.response.CreditsResponse
import com.mendelin.tmdb_koin.data.model.response.TvShowDetailsResponse
import com.mendelin.tmdb_koin.data.repository.remote.TvShowsRepository

class TvShowSeasonsViewModel(private val repo: TvShowsRepository) : BaseViewModel() {
    private val tvDetails = MutableLiveData<TvShowDetailsResponse>()
    private val tvCredits = MutableLiveData<CreditsResponse>()
    private val tvSeasons = MutableLiveData<List<SeasonItem>>()

    fun fetchTvShowDetails(tv_id: Int) {
        RetrofitResponseHandler<TvShowDetailsResponse>(
            { details ->
                tvDetails.postValue(details)
                tvSeasons.postValue(details.seasons)
                isLoading.value = false
            },
            { errorMsg ->
                error.postValue(errorMsg)
                isLoading.value = false
            })
            .processData(tv_id, repo::getTvShowDetails)
    }

    fun fetchTvShowCredits(tv_id: Int) {
        RetrofitResponseHandler<CreditsResponse>(
            { credits ->
                tvCredits.postValue(credits)
                isLoading.value = false
            },
            { errorMsg ->
                error.postValue(errorMsg)
                isLoading.value = false
            })
            .processData(tv_id, repo::getTvShowCredits)
    }

    val details: LiveData<TvShowDetailsResponse>
        get() = tvDetails

    val credits: LiveData<CreditsResponse>
        get() = tvCredits

    val seasons: LiveData<List<SeasonItem>>
        get() = tvSeasons
}