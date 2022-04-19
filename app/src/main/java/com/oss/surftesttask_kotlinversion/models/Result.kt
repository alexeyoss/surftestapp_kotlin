package com.oss.surftesttask_kotlinversion.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result() {
    @SerializedName("adult")
    @Expose
    var isAdult = false

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null

    @SerializedName("genre_ids")
    @Expose
    var genreIds: List<Int>? = null

    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null

    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null

    @SerializedName("overview")
    @Expose
    var overview: String? = null

    @SerializedName("popularity")
    @Expose
    var popularity = 0.0

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null

    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("video")
    @Expose
    var isVideo = false

    @SerializedName("vote_average")
    @Expose
    var voteAverage = 0.0

    @SerializedName("vote_count")
    @Expose
    var voteCount = 0

}