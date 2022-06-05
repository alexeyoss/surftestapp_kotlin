package com.oss.surftesttask_kotlinversion.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oss.surftesttask_kotlinversion.data.db.entities.ResultCacheEntity

@Dao
interface ResultDao {

    // TODO insert only new data + think about the like
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(resultEntity: ResultCacheEntity): Long

    @Query("SELECT * FROM liked_movies")
    suspend fun get(): List<ResultCacheEntity>
}