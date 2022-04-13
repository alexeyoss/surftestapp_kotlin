package com.oss.surftesttask_kotlinversion.network

import com.oss.surftesttask_kotlinversion.models.PostModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/{apiVersion}/discover/movie")
    fun getData(
        @Path("apiVersion") apiVersion: String?,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("sort_by") sort_by: String?,
        @Query("include_adult") include_adult: Boolean?,
        @Query("include_video") include_video: Boolean?,
        @Query("with_watch_monetization_types") with_watch_monetization_types: String?
    ): Call<PostModel>

    @GET("/{apiVersion}/search/movie")
    fun getSearchData(
        @Path("apiVersion") apiVersion: String?,
        @Query("api_key") apiKey: String?,
        @Query("query") query: String?
    ): Call<PostModel>

//    @GET("/{apiVersion}/search/movie")
//    fun newSearchFilm(
//        @Path("apiVersion") apiVersion: String?,
//        @Query("api_key") apiKey: String?,
//        @Query("query") query: String?
//    ): Single<PostModel>?
//
//    @GET("/{apiVersion}/discover/movie")
//    fun newGetInitData(
//        @Path("apiVersion") apiVersion: String?,
//        @Query("api_key") apiKey: String?,
//        @Query("language") language: String?,
//        @Query("sort_by") sort_by: String?,
//        @Query("include_adult") include_adult: Boolean?,
//        @Query("include_video") include_video: Boolean?,
//        @Query("with_watch_monetization_types") with_watch_monetization_types: String?
//    ): Single<PostModel>?
}
