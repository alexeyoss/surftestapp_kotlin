package com.oss.surftesttask_kotlinversion.di

import com.oss.surftesttask_kotlinversion.data.db.ResultDao
import com.oss.surftesttask_kotlinversion.data.db.entities.ResultCacheMapper
import com.oss.surftesttask_kotlinversion.data.repository.Repository
import com.oss.surftesttask_kotlinversion.retrofit.ApiService
import com.oss.surftesttask_kotlinversion.retrofit.entities.NetworkMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    @Named("Repository")
    fun provideRepository(
        resultDao: ResultDao,
        retrofit: ApiService,
        cacheMapper: ResultCacheMapper,
        networkMapper: NetworkMapper
    ): Repository {
        return Repository(resultDao, retrofit, cacheMapper, networkMapper)
    }
}