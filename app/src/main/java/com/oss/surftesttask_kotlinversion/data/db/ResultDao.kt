package com.oss.surftesttask_kotlinversion.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oss.surftesttask_kotlinversion.data.db.entities.ResultCacheEntity

@Dao
interface ResultDao {

    // TODO insert only new data + think about the like
    // TODO Abort trigger the Exception in the Interactor
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(resultEntity: ResultCacheEntity): Long

    @Query("SELECT * FROM cached_movies")
    suspend fun get(): List<ResultCacheEntity>

    @Query("UPDATE cached_movies SET liked = :isLiked WHERE id == :movieId")
    suspend fun setLikedMovieStatus(movieId: Int, isLiked: Boolean): Int

    @Query("SELECT liked FROM cached_movies WHERE id == :movieId")
    suspend fun getLikedMovieStatus(movieId: Int): Boolean

}