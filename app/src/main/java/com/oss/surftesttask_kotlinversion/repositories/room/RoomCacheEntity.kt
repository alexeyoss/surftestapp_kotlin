package com.oss.surftesttask_kotlinversion.repositories.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oss.surftesttask_kotlinversion.utils.Constants

@Entity(tableName = "liked_movies")
data class RoomCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "adult")
    var isAdult: Boolean,

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String,

    @ColumnInfo(name = "genre_ids")
    var genreIds: List<Int>,

    @ColumnInfo(name = "original_language")
    var originalLanguage: String,

    @ColumnInfo(name = "original_title")
    var originalTitle: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "popularity")
    var popularity: Float,

    @ColumnInfo(name = "posterPath")
    var posterPath: String,

    @ColumnInfo(name = "release_date")
    var releaseDate: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "video")
    var isVideoisVideo: Boolean,

    @ColumnInfo(name = "vote_average")
    var voteAverage: Float,

    @ColumnInfo(name = "vote_count")
    var voteCount: Int,

    @ColumnInfo(name = "liked", defaultValue = "false")
    var liked: Boolean
)