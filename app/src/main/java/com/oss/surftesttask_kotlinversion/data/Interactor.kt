package com.oss.surftesttask_kotlinversion.data

import com.oss.surftesttask_kotlinversion.data.db.ResultDao
import com.oss.surftesttask_kotlinversion.data.db.entities.ResultCacheMapper
import com.oss.surftesttask_kotlinversion.models.Results
import com.oss.surftesttask_kotlinversion.retrofit.ApiService
import com.oss.surftesttask_kotlinversion.retrofit.entities.NetworkMapper
import com.oss.surftesttask_kotlinversion.retrofit.entities.ResultsNetworkEntity
import com.oss.surftesttask_kotlinversion.utils.DataState
import com.oss.surftesttask_kotlinversion.utils.LikeState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Interactor(
    private val resultDao: ResultDao,
    private val retrofit: ApiService,
    private val resultMapper: ResultCacheMapper,
    private val networkMapper: NetworkMapper
) {
    private lateinit var networkResult: List<ResultsNetworkEntity>

    suspend fun getMoviesFromNetwork(query: String? = null): Flow<DataState<List<Results>>> = flow {
        try {
            if (query != null && query.isNotEmpty()) {
                networkResult = retrofit.getSearchData(
                    API_VERSION,
                    API_KEY,
                    query
                ).results
            } else {
                networkResult = retrofit.getData(
                    API_VERSION,
                    API_KEY,
                    DEFAULT_LANGUAGE,
                    DEFAULT_SORT_BY,
                    DEFAULT_INCLUDE_ADULT,
                    DEFAULT_INCLUDE_VIDEO,
                    WITH_WATCH_MONETIZATION_TYPES
                ).results
            }

            val results = networkMapper.mapFromEntityList(networkResult)
            emit(DataState.Loading(results))

//            for (item in results) {
//                resultDao.insert(resultMapper.mapResultToEntity(item, false))
//            }
//            val cachedResults = resultDao.get()
//            emit(DataState.Success(resultMapper.mapFromEntityList(cachedResults)))
            emit(DataState.Success(results))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun setLikedMovieStatus(movieId: Int, isLiked: Boolean): Flow<LikeState<Int>> =
        flow {
            try {
                emit(LikeState.Loading)
                val dbLikeStatus = resultDao.getLikedMovieStatus(movieId)
                if (dbLikeStatus != isLiked) {
                    val updatedRows = resultDao.setLikedMovieStatus(movieId, isLiked)
                    emit(LikeState.Success(updatedRows))
                }
            } catch (e: Exception) {
                emit(LikeState.Error(e))
            }
        }


    companion object {
        const val API_VERSION = "3"
        const val API_KEY = "50bd34c2f45cba21762125b1c6069573"
        const val DEFAULT_LANGUAGE = "en-US"
        const val DEFAULT_SORT_BY = "popularity.desc"
        const val DEFAULT_INCLUDE_ADULT = false
        const val DEFAULT_INCLUDE_VIDEO = false
        const val WITH_WATCH_MONETIZATION_TYPES = "flatrate"
    }
}