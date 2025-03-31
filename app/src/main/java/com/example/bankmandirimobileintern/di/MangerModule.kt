package com.example.bankmandirimobileintern.di

import com.example.bankmandirimobileintern.data.manager.LocalUserManagerImpl
import com.example.bankmandirimobileintern.domain.manager.LocalUserManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MangerModule {

    @Binds
    @Singleton
    abstract fun bindLocalUserManger(localUserMangerImpl: LocalUserManagerImpl) : LocalUserManager
}