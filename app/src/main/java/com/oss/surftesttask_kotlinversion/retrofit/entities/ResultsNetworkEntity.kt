package com.oss.surftesttask_kotlinversion.retrofit.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResultsNetworkEntity(
    @SerializedName("adult")
    @Expose
    var isAdult: Boolean,

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String,

    @SerializedName("genre_ids")
    @Expose
    var genreIds: String,

    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("original_language")
    @Expose
    var originalLanguage: String,

    @SerializedName("original_title")
    @Expose
    var originalTitle: String,

    @SerializedName("overview")
    @Expose
    var overview: String,

    @SerializedName("popularity")
    @Expose
    var popularity: Float,

    @SerializedName("poster_path")
    @Expose
    var posterPath: String,

    @SerializedName("release_date")
    @Expose
    var releaseDate: String,

    @SerializedName("title")
    @Expose
    var title: String,

    @SerializedName("video")
    @Expose
    var isVideoisVideo: Boolean,

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Float,

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int,
)
