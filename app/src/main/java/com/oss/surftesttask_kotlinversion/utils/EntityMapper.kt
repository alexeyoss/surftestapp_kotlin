package com.oss.surftesttask_kotlinversion.utils

interface EntityMapper<ResultsEntity, DomainResultModel> {
//    fun mapPostModelFromEntity(entity: PostModelEntity): DomainPostModel
//
//    fun mapPostModelToEntity(domainModel: DomainPostModel): PostModelEntity

    fun mapResultFromEntity(entity: ResultsEntity): DomainResultModel

    fun mapResultToEntity(domainModel: DomainResultModel, liked:Boolean): ResultsEntity

}