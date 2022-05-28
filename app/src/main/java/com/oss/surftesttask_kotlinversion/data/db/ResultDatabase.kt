package com.oss.surftesttask_kotlinversion.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oss.surftesttask_kotlinversion.data.db.ResultDatabase.Companion.DB_VERSION
import com.oss.surftesttask_kotlinversion.data.db.entities.ResultCacheEntity

@Database(entities = [ResultCacheEntity::class], version = DB_VERSION, exportSchema = false)
abstract class ResultDatabase : RoomDatabase() {

    abstract fun resultDao(): ResultDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "result_db"
    }
}
