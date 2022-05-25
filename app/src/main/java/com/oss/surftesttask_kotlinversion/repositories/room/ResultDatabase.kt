package com.oss.surftesttask_kotlinversion.repositories.room

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.oss.surftesttask_kotlinversion.utils.Constants

@Database(entities = [RoomCacheEntity::class], version = Constants.DB_VERSION, exportSchema = false)
abstract class ResultDatabase : RoomDatabase() {

    abstract fun resultDao(): ResultDao

    companion object {
        @JvmStatic val DATABASE_NAME: String = Constants.DB_NAME
    }
}
