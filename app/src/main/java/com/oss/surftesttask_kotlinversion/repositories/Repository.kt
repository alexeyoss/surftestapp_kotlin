package com.oss.surftesttask_kotlinversion.repositories

import com.oss.surftesttask_kotlinversion.models.Results
import com.oss.surftesttask_kotlinversion.repositories.room.ResultDao
import com.oss.surftesttask_kotlinversion.repositories.room.RoomCacheMapper
import com.oss.surftesttask_kotlinversion.retrofit.ApiService
import com.oss.surftesttask_kotlinversion.retrofit.NetworkMapper
import com.oss.surftesttask_kotlinversion.utils.Constants
import com.oss.surftesttask_kotlinversion.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository
@Inject
constructor(
    private val resultDao: ResultDao,
    private val retrofit: ApiService,
    private val cacheMapper: RoomCacheMapper,
    private val networkMapper: NetworkMapper
) {
    suspend fun getMovies(): Flow<DataState<List<Results>>> = flow {
        emit(DataState.Loading)
        try {
            val networkResult = retrofit.getData(
                Constants.API_VERSION,
                Constants.API_KEY,
                Constants.DEFAULT_LANGUAGE,
                Constants.DEFAULT_SORT_BY,
                Constants.DEFAULT_INCLUDE_ADULT,
                Constants.DEFAULT_INCLUDE_VIDEO,
                Constants.WITH_WATCH_MONETIZATION_TYPES
            ).results
            val results = networkMapper.mapFromEntityList(networkResult)
            for (item in results) {
                resultDao.insert(cacheMapper.mapResultToEntity(item, false))
            }
            val cachedResults = resultDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedResults)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}