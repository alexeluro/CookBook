package com.inspiredcoda.cookbook.di

import android.content.Context
import com.google.gson.Gson
import com.inspiredcoda.cookbook.data.FruitRepository
import com.inspiredcoda.cookbook.data.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesGson() = Gson()

    @Singleton
    @Provides
    fun provideRepository(@ApplicationContext context: Context, gson: Gson): Repository {
        return FruitRepository(context, gson)
    }

}