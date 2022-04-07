package com.mendelin.tmdb_koin.ui.tv_show_season_episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mendelin.tmdb_koin.base.BaseViewModel
import com.mendelin.tmdb_koin.base.RetrofitResponseHandler
import com.mendelin.tmdb_koin.data.model.entity.EpisodeItem
import com.mendelin.tmdb_koin.data.model.response.TvSeasonDetailsResponse
import com.mendelin.tmdb_koin.data.repository.remote.TvShowsRepository

class TvShowSeasonEpisodesViewModel(private val repo: TvShowsRepository) : BaseViewModel() {
    private val seasonDetails = MutableLiveData<TvSeasonDetailsResponse>()
    private val episodeList = MutableLiveData<List<EpisodeItem>>()

    fun fetchTvShowDetails(tv_id: Int, season: Int) {
        /*val exceptionHandler = CoroutineExceptionHandler { _, ex ->
            Timber.e("Exception ${ex.localizedMessage}")
        }

        val scope = CoroutineScope(Dispatchers.IO + exceptionHandler)

        scope.launch {
            val details = repo.getTvSeasonDetails(tv_id, season)
            seasonDetails.postValue(details)
            episodeList.postValue(details.episodes)
        }*/

        isLoading.value = true
        RetrofitResponseHandler<TvSeasonDetailsResponse>(
            { response ->
                seasonDetails.postValue(response)
                episodeList.postValue(response.episodes)
                isLoading.value = false
            },
            { errorMsg ->
                error.postValue(errorMsg)
                isLoading.value = false
            })
            .processSeasonData(tv_id, season, repo::getTvSeasonDetails)

    }

    val details: LiveData<TvSeasonDetailsResponse>
        get() = seasonDetails

    val seasons: LiveData<List<EpisodeItem>>
        get() = episodeList
}