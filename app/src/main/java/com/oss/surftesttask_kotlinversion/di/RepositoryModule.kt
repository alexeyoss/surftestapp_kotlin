package com.oss.surftesttask_kotlinversion.di

import com.oss.surftesttask_kotlinversion.data.db.ResultDao
import com.oss.surftesttask_kotlinversion.data.db.entities.ResultCacheMapper
import com.oss.surftesttask_kotlinversion.data.Interactor
import com.oss.surftesttask_kotlinversion.retrofit.ApiService
import com.oss.surftesttask_kotlinversion.retrofit.entities.NetworkMapper
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
    fun provideResultCacheMapper():ResultCacheMapper = ResultCacheMapper()

    @Singleton
    @Provides
    fun provideNetworkMapper():NetworkMapper = NetworkMapper()

    @Singleton
    @Provides
    fun provideRepository(
        resultDao: ResultDao,
        retrofit: ApiService,
        resultMapper: ResultCacheMapper,
        networkMapper: NetworkMapper
    ): Interactor {
        return Interactor(
            resultDao,
            retrofit,
            resultMapper,
            networkMapper
        )
    }
}