package com.oss.surftesttask_kotlinversion.repositories.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(resultEntity: RoomCacheEntity): Long

    @Query("SELECT * FROM liked_movies")
    suspend fun get(): List<RoomCacheEntity>
}