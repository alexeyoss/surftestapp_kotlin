package com.oss.surftesttask_kotlinversion.di

import com.oss.surftesttask_kotlinversion.repositories.Repository
import com.oss.surftesttask_kotlinversion.repositories.room.ResultDao
import com.oss.surftesttask_kotlinversion.repositories.room.RoomCacheMapper
import com.oss.surftesttask_kotlinversion.retrofit.ApiService
import com.oss.surftesttask_kotlinversion.retrofit.NetworkMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        resultDao: ResultDao,
        retrofit: ApiService,
        cacheMapper: RoomCacheMapper,
        networkMapper: NetworkMapper
    ): Repository {
        return Repository(resultDao, retrofit, cacheMapper, networkMapper)
    }
}