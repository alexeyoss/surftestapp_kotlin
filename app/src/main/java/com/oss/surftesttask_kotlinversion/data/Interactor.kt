package com.oss.surftesttask_kotlinversion.data

import com.oss.surftesttask_kotlinversion.data.db.ResultDao
import com.oss.surftesttask_kotlinversion.data.db.entities.ResultCacheEntity
import com.oss.surftesttask_kotlinversion.data.db.entities.ResultCacheMapper
import com.oss.surftesttask_kotlinversion.models.Results
import com.oss.surftesttask_kotlinversion.models.States.DataState
import com.oss.surftesttask_kotlinversion.models.States.LikeState
import com.oss.surftesttask_kotlinversion.retrofit.ApiService
import com.oss.surftesttask_kotlinversion.retrofit.entities.NetworkMapper
import com.oss.surftesttask_kotlinversion.retrofit.entities.ResultsNetworkEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Interactor(
    private val resultDao: ResultDao,
    private val retrofit: ApiService,
    private val resultCacheMapper: ResultCacheMapper,
    private val networkMapper: NetworkMapper
) {
    private lateinit var networkResult: List<ResultsNetworkEntity>

    suspend fun getCachedMoviesFromDb(query: String? = null): Flow<DataState<List<Results>>> =
        flow {
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
                //TODO Need to streamline the caching process, cause when we update the UI after
                // LIKE operation, we trigger the update by running the full network request, it's
                // illogical at the resources point
                val resultsFromNetworkModel = networkMapper.mapFromEntityList(networkResult)
                emit(DataState.Loading(resultsFromNetworkModel))

                val cachedResults = cacheResourcesIntoDb(resultsFromNetworkModel)

                cachedResults?.let {
                    val resultsFromDbModel = resultCacheMapper.mapFromEntityList(cachedResults)
                    emit(DataState.Success(resultsFromDbModel))
                } ?: run {
                    emit(DataState.Success(resultsFromNetworkModel))
                }

            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }

    private suspend fun cacheResourcesIntoDb(data: List<Results>): List<ResultCacheEntity>? {
        return try {
            val moviesIDs = mutableListOf<Int>()

            for (item in data) {
                moviesIDs.add(item.id)

                resultDao.insert(resultCacheMapper.mapResultToEntity(item, false))
            }
            val cachedResults = resultDao.getByIDs(moviesIDs)
            cachedResults
        } catch (e: Exception) {
            null
        }
    }

    suspend fun setLikedMovieStatus(
        movieId: Int,
        isLiked: Boolean
    ): Flow<LikeState<Any?>> =
        flow {
            try {
                emit(LikeState.Loading)
                val dbLikeStatus = resultDao.getLikedMovieStatus(movieId)
                if (dbLikeStatus != isLiked) {
                    resultDao.setLikedMovieStatus(movieId, isLiked)
                    emit(LikeState.Success(isLiked))
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