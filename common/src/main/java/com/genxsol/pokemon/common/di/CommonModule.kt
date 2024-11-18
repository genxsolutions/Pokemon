package com.genxsol.pokemon.common.di

import com.genxsol.pokemon.common.dispatcher.DefaultDispatcherProvider
import com.genxsol.pokemon.common.dispatcher.DispatcherProvider
import com.genxsol.pokemon.common.logger.AppLogger
import com.genxsol.pokemon.common.logger.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {

  @Provides
  @Singleton
  fun providesDispatcher(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    @Singleton
    fun provideLogger(): Logger = AppLogger()
}