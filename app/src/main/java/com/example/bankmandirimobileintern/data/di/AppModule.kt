package com.example.bankmandirimobileintern.data.di

import android.app.Application
import com.example.bankmandirimobileintern.data.manager.LocalUserManagerImpl
import com.example.bankmandirimobileintern.data.remote.dto.NewsApi
import com.example.bankmandirimobileintern.data.repository.NewsRepositoryImpl
import com.example.bankmandirimobileintern.domain.manager.LocalUserManager
import com.example.bankmandirimobileintern.domain.manager.repository.NewsRepository
import com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.AppEntryUseCases
import com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.ReadAppEntry
import com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.SaveAppEntry
import com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.news.GetNews
import com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.news.NewsUseCases
import com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.news.SearchNews
import com.example.bankmandirimobileintern.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Provides
    @Singleton
    fun provideApiInstance(): NewsApi {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository {
        return NewsRepositoryImpl(newsApi)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository)
        )
    }

}