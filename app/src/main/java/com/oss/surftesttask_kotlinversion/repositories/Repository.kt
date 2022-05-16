package com.oss.surftesttask_kotlinversion.repositories

import com.oss.surftesttask_kotlinversion.models.Results
import com.oss.surftesttask_kotlinversion.repositories.room.ResultDao
import com.oss.surftesttask_kotlinversion.repositories.room.RoomCacheMapper
import com.oss.surftesttask_kotlinversion.retrofit.ApiService
import com.oss.surftesttask_kotlinversion.retrofit.NetworkMapper
import com.oss.surftesttask_kotlinversion.utils.Constants
import com.oss.surftesttask_kotlinversion.utils.DataState
import dagger.hilt.android.scopes.ServiceScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ServiceScoped
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
//    suspend fun getMovies(): Response<PostModelNetworkEntity> {
//        return api.getData(
//            Constants.API_VERSION,
//            Constants.API_KEY,
//            Constants.DEFAULT_LANGUAGE,
//            Constants.DEFAULT_SORT_BY,
//            Constants.DEFAULT_INCLUDE_ADULT,
//            Constants.DEFAULT_INCLUDE_VIDEO,
//            Constants.WITH_WATCH_MONETIZATION_TYPES
//        )
//    }
//
//    suspend fun getSearchMovies(query: String): Response<PostModelNetworkEntity> {
//        return api.getSearchData(
//            Constants.API_VERSION,
//            Constants.API_KEY,
//            query
//        )
//    }
}