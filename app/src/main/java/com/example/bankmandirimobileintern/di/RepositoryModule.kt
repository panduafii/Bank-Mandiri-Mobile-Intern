package com.example.bankmandirimobileintern.di

import com.example.bankmandirimobileintern.data.repository.NewsRepositoryImpl
import com.example.bankmandirimobileintern.domain.manager.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository

}