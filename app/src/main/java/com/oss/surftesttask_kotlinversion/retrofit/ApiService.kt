package com.oss.surftesttask_kotlinversion.retrofit

import com.oss.surftesttask_kotlinversion.retrofit.entities.PostModelNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/{apiVersion}/discover/movie")
    suspend fun getData(
        @Path("apiVersion") apiVersion: String?,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("sort_by") sort_by: String?,
        @Query("include_adult") include_adult: Boolean?,
        @Query("include_video") include_video: Boolean?,
        @Query("with_watch_monetization_types") with_watch_monetization_types: String?
    ): PostModelNetworkEntity

    @GET("/{apiVersion}/search/movie")
    suspend fun getSearchData(
        @Path("apiVersion") apiVersion: String?,
        @Query("api_key") apiKey: String?,
        @Query("query") query: String?
    ): PostModelNetworkEntity

}
