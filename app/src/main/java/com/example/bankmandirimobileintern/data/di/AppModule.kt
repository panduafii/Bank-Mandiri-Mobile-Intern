package com.example.bankmandirimobileintern.data.di

import android.app.Application
import com.example.bankmandirimobileintern.data.manager.LocalUserManagerImpl
import com.example.bankmandirimobileintern.domain.manager.LocalUserManager
import com.example.bankmandirimobileintern.domain.manager.usecase.AppEntryUseCases
import com.example.bankmandirimobileintern.domain.manager.usecase.ReadAppEntry
import com.example.bankmandirimobileintern.domain.manager.usecase.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(context = application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManager
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )

}