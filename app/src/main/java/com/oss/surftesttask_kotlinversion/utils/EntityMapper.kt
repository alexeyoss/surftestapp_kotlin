package com.oss.surftesttask_kotlinversion.utils

interface EntityMapper<ResultsEntity, DomainResultModel> {

    fun mapResultFromEntity(entity: ResultsEntity): DomainResultModel

    fun mapResultToEntity(domainModel: DomainResultModel, liked:Boolean): ResultsEntity

}