package com.oss.surftesttask_kotlinversion.models

data class Results(
    var isAdult: Boolean,
    var backdropPath: String,
    var genreIds: String,
    var id: Int,
    var originalLanguage: String,
    var originalTitle: String,
    var overview: String,
    var popularity: Float,
    var posterPath: String,
    var releaseDate: String,
    var title: String,
    var isVideoisVideo: Boolean,
    var voteAverage: Float,
    var voteCount: Int,
    var liked: Boolean = DEFAULT_LIKED
) {
    companion object {
        const val DEFAULT_LIKED = false
    }
}

