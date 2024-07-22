package com.sameer.dogbreeds.di

import com.sameer.dogbreeds.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkUrlModule {

    @Provides
    @Singleton
    fun provideUrl(): String {
        return BASE_URL
    }
}