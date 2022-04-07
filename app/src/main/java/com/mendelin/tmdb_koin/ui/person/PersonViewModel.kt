package com.mendelin.tmdb_koin.ui.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mendelin.tmdb_koin.base.BaseViewModel
import com.mendelin.tmdb_koin.base.RetrofitResponseHandler
import com.mendelin.tmdb_koin.data.model.response.PersonDetailsResponse
import com.mendelin.tmdb_koin.data.model.response.PersonMovieCreditsResponse
import com.mendelin.tmdb_koin.data.model.response.PersonTvCreditsResponse
import com.mendelin.tmdb_koin.data.repository.remote.PeopleRepository

class PersonViewModel(private val repo: PeopleRepository) : BaseViewModel() {
    private val personDetails = MutableLiveData<PersonDetailsResponse>()
    private val personMovieCredits = MutableLiveData<PersonMovieCreditsResponse>()
    private val personTvCredits = MutableLiveData<PersonTvCreditsResponse>()

    fun fetchPersonDetails(person_id: Int) {
        isLoading.value = true
        RetrofitResponseHandler<PersonDetailsResponse>(
            { response ->
                personDetails.postValue(response)
                isLoading.value = false
            },
            { errorMsg ->
                error.postValue(errorMsg)
                isLoading.value = false
            })
            .processData(person_id, repo::getPersonDetails)
    }

    fun fetchMovieCredits(person_id: Int) {
        isLoading.value = true
        RetrofitResponseHandler<PersonMovieCreditsResponse>(
            { response ->
                personMovieCredits.postValue(response)
                isLoading.value = false
            },

            { errorMsg ->
                error.postValue(errorMsg)
                isLoading.value = false
            })
            .processData(person_id, repo::getPersonMovieCredits)
    }

    fun fetchTvCredits(person_id: Int) {
        isLoading.value = true
        RetrofitResponseHandler<PersonTvCreditsResponse>(
            { response ->
                personTvCredits.postValue(response)
                isLoading.value = false
            },

            { errorMsg ->
                error.postValue(errorMsg)
                isLoading.value = false
            })
            .processData(person_id, repo::getPersonTvCredits)
    }

    val details: LiveData<PersonDetailsResponse>
        get() = personDetails

    val movieCredits: LiveData<PersonMovieCreditsResponse>
        get() = personMovieCredits

    val tvCredits: LiveData<PersonTvCreditsResponse>
        get() = personTvCredits
}