package com.example.bankmandirimobileintern.di

import android.app.Application
import androidx.room.Room
import com.example.bankmandirimobileintern.data.local.NewsDao
import com.example.bankmandirimobileintern.data.local.NewsDatabase
import com.example.bankmandirimobileintern.data.local.NewsTypeConvertor
import com.example.bankmandirimobileintern.data.manager.LocalUserManagerImpl
import com.example.bankmandirimobileintern.data.remote.dto.NewsApi
import com.example.bankmandirimobileintern.data.repository.NewsRepositoryImpl
import com.example.bankmandirimobileintern.domain.manager.LocalUserManager
import com.example.bankmandirimobileintern.domain.manager.repository.NewsRepository
import com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.AppEntryUseCases
import com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.ReadAppEntry
import com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.SaveAppEntry
import com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.news.DeleteArticle
import com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.news.GetArticle
import com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.news.GetArticles
import com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.news.GetNews
import com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.news.NewsUseCases
import com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.news.SearchNews
import com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.news.UpsertArticle
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
        localUserManager: LocalUserManager
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
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
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository {
        return NewsRepositoryImpl(newsApi,newsDao)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsDao),
            deleteArticle = DeleteArticle(newsDao),
            getArticles = GetArticles(newsDao),
            getArticle = GetArticle(newsDao)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = "news_db"
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao

}