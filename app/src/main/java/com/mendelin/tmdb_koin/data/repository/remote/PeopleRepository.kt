package com.mendelin.tmdb_koin.data.repository.remote

import com.mendelin.tmdb_koin.data.api.TmdbDataSource
import com.mendelin.tmdb_koin.data.model.response.PersonDetailsResponse
import com.mendelin.tmdb_koin.data.model.response.PersonMovieCreditsResponse
import com.mendelin.tmdb_koin.data.model.response.PersonTvCreditsResponse
import retrofit2.Response

class PeopleRepository(private val service: TmdbDataSource) {
    suspend fun getPersonDetails(person_id: Int): Response<PersonDetailsResponse> =
        service.getPersonDetails(person_id)

    suspend fun getPersonMovieCredits(person_id: Int): Response<PersonMovieCreditsResponse> =
        service.getPersonMovieCredits(person_id)

    suspend fun getPersonTvCredits(person_id: Int): Response<PersonTvCreditsResponse> =
        service.getPersonTvCredits(person_id)
}