package com.oss.surftesttask_kotlinversion.repositories.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oss.surftesttask_kotlinversion.utils.Constants

@Database(entities = [RoomCacheEntity::class], version = Constants.DB_VERSION)
abstract class ResultDatabase : RoomDatabase() {

    abstract fun resultDao(): ResultDao

    companion object {
        val DATABASE_NAME: String = Constants.DB_NAME
    }
}