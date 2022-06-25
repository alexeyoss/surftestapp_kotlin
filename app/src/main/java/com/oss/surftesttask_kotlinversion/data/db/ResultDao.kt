package com.oss.surftesttask_kotlinversion.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oss.surftesttask_kotlinversion.data.db.entities.ResultCacheEntity

@Dao
interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(resultEntity: ResultCacheEntity): Long

    @Query("SELECT * FROM cached_movies")
    suspend fun getAll(): List<ResultCacheEntity>

    @Query("SELECT * FROM cached_movies WHERE id IN (:movieIDs)")
    suspend fun getByIDs(movieIDs: List<Int>): List<ResultCacheEntity>

    @Query("UPDATE cached_movies SET liked = :isLiked WHERE id == :movieId")
    suspend fun setLikedMovieStatus(movieId: Int, isLiked: Boolean)

    @Query("SELECT liked FROM cached_movies WHERE id == :movieId")
    suspend fun getLikedMovieStatus(movieId: Int): Boolean
}