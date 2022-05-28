package com.oss.surftesttask_kotlinversion.di

import com.oss.surftesttask_kotlinversion.data.repository.Repository
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
    fun provideRepository(): Repository = Repository()
}