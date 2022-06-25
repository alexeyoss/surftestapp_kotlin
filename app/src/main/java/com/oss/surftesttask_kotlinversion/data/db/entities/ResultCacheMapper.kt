package com.oss.surftesttask_kotlinversion.data.db.entities

import com.oss.surftesttask_kotlinversion.models.Results
import com.oss.surftesttask_kotlinversion.utils.Constants
import com.oss.surftesttask_kotlinversion.utils.EntityMapper
import javax.inject.Inject


class ResultCacheMapper
@Inject constructor() :
    EntityMapper<ResultCacheEntity, Results> {
    override fun mapResultFromEntity(entity: ResultCacheEntity): Results {
        return Results(
            id = entity.id,
            isAdult = entity.isAdult,
            backdropPath = entity.backdropPath,
            genreIds = entity.genreIds,
            originalLanguage = entity.originalLanguage,
            originalTitle = entity.originalTitle,
            overview = entity.overview,
            popularity = entity.popularity,
            posterPath = entity.posterPath,
            releaseDate = entity.releaseDate,
            title = entity.title,
            isVideoisVideo = entity.isVideoisVideo,
            voteAverage = entity.voteAverage,
            voteCount = entity.voteCount,
            liked = entity.liked
        )
    }

    override fun mapResultToEntity(domainModel: Results, liked: Boolean): ResultCacheEntity {
        return ResultCacheEntity(
            id = domainModel.id,
            isAdult = domainModel.isAdult,
            backdropPath = domainModel.backdropPath,
            genreIds = domainModel.genreIds,
            originalLanguage = domainModel.originalLanguage,
            originalTitle = domainModel.originalTitle,
            overview = domainModel.overview,
            popularity = domainModel.popularity,
            posterPath = domainModel.posterPath,
            releaseDate = domainModel.releaseDate,
            title = domainModel.title,
            isVideoisVideo = domainModel.isVideoisVideo,
            voteAverage = domainModel.voteAverage,
            voteCount = domainModel.voteCount,
            liked = liked
        )
    }

    fun mapFromEntityList(entity: List<ResultCacheEntity>): List<Results> {
        return entity.map { mapResultFromEntity(it) }
    }

}