package com.oss.surftesttask_kotlinversion.repositories

import com.oss.surftesttask_kotlinversion.models.PostModel
import com.oss.surftesttask_kotlinversion.network.ApiService
import com.oss.surftesttask_kotlinversion.network.Client
import com.oss.surftesttask_kotlinversion.support.Constants
import retrofit2.Response

object Repository {

    private val api: ApiService = Client.getApiService()

    suspend fun getMovies(): Response<PostModel> {
        return api.getData(
            Constants.API_VERSION,
            Constants.API_KEY,
            Constants.DEFAULT_LANGUAGE,
            Constants.DEFAULT_SORT_BY,
            Constants.DEFAULT_INCLUDE_ADULT,
            Constants.DEFAULT_INCLUDE_VIDEO,
            Constants.WITH_WATCH_MONETIZATION_TYPES
        )
    }

    suspend fun getSearchMovies(query: String): Response<PostModel> {
        return api.getSearchData(
            Constants.API_VERSION,
            Constants.API_KEY,
            query
        )
    }
}

