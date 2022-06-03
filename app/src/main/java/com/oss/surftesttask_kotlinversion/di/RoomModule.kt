package com.oss.surftesttask_kotlinversion.di

import android.content.Context
import androidx.room.Room
import com.oss.surftesttask_kotlinversion.data.db.ResultDao
import com.oss.surftesttask_kotlinversion.data.db.ResultDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideResultDB(
        @ApplicationContext context: Context,
    ): ResultDatabase {
        return Room.databaseBuilder(
            context,
            ResultDatabase::class.java,
            ResultDatabase.DB_NAME,
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideResultDao(resultDatabase: ResultDatabase): ResultDao {
        return resultDatabase.resultDao()
    }
}