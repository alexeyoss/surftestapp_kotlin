package com.oss.surftesttask_kotlinversion.repositories.room

import com.oss.surftesttask_kotlinversion.models.Results
import com.oss.surftesttask_kotlinversion.utils.EntityMapper
import javax.inject.Inject


class RoomCacheMapper
@Inject constructor() :
    EntityMapper<RoomCacheEntity, Results> {
    override fun mapResultFromEntity(entity: RoomCacheEntity): Results {
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
            voteCount = entity.voteCount
        )
    }

    override fun mapResultToEntity(domainModel: Results, liked: Boolean): RoomCacheEntity {
        return RoomCacheEntity(
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

    fun mapFromEntityList(entity: List<RoomCacheEntity>): List<Results> {
        return entity.map { mapResultFromEntity(it) }
    }

}