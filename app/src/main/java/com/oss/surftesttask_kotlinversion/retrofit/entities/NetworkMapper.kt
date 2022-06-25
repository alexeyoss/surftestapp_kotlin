package com.oss.surftesttask_kotlinversion.retrofit.entities

import com.oss.surftesttask_kotlinversion.models.Results
import com.oss.surftesttask_kotlinversion.utils.Constants
import com.oss.surftesttask_kotlinversion.utils.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : EntityMapper<ResultsNetworkEntity, Results> {

    override fun mapResultFromEntity(entity: ResultsNetworkEntity): Results {
        return Results(
            isAdult = entity.isAdult,
            backdropPath = Constants.IMG_URL + entity.backdropPath,
            genreIds = entity.genreIds,
            id = entity.id,
            originalLanguage = entity.originalLanguage,
            originalTitle = entity.originalTitle,
            overview = entity.overview,
            popularity = entity.popularity,
            posterPath = Constants.IMG_URL + entity.posterPath,
            releaseDate = entity.releaseDate,
            title = entity.title,
            isVideoisVideo = entity.isVideoisVideo,
            voteAverage = entity.voteAverage,
            voteCount = entity.voteCount,
            liked = DEFAULT_LIKE
        )
    }

    override fun mapResultToEntity(domainModel: Results, liked: Boolean): ResultsNetworkEntity {
        return ResultsNetworkEntity(
            isAdult = domainModel.isAdult,
            backdropPath = domainModel.backdropPath,
            genreIds = domainModel.genreIds,
            id = domainModel.id,
            originalLanguage = domainModel.originalLanguage,
            originalTitle = domainModel.originalTitle,
            overview = domainModel.overview,
            popularity = domainModel.popularity,
            posterPath = domainModel.posterPath,
            releaseDate = domainModel.releaseDate,
            title = domainModel.title,
            isVideoisVideo = domainModel.isVideoisVideo,
            voteAverage = domainModel.voteAverage,
            voteCount = domainModel.voteCount
        )
    }

    fun mapFromEntityList(entities: List<ResultsNetworkEntity>): List<Results> {
        return entities.map { mapResultFromEntity(it) }
    }

    companion object {
        @JvmStatic
        private val DEFAULT_LIKE = false
    }
}