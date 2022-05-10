package com.oss.surftesttask_kotlinversion.repositories

import com.oss.surftesttask_kotlinversion.retrofit.PostModelNetworkEntity
import com.oss.surftesttask_kotlinversion.retrofit.ApiService
import com.oss.surftesttask_kotlinversion.retrofit.Client
import com.oss.surftesttask_kotlinversion.utils.Constants
import dagger.hilt.android.scopes.ServiceScoped
import retrofit2.Response

@ServiceScoped
class Repository {
    private lateinit var apiService: ApiService
    private val api: ApiService = Client(
        Constants.BASE_URL,
        apiService
    ).getApiService()

    suspend fun getMovies(): Response<PostModelNetworkEntity> {
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

    suspend fun getSearchMovies(query: String): Response<PostModelNetworkEntity> {
        return api.getSearchData(
            Constants.API_VERSION,
            Constants.API_KEY,
            query
        )
    }
}