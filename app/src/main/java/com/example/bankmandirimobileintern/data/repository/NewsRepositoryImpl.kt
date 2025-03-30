package com.example.bankmandirimobileintern.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.bankmandirimobileintern.data.remote.dto.NewsApi
import com.example.bankmandirimobileintern.data.remote.dto.NewsPagingSource
import com.example.bankmandirimobileintern.domain.manager.model.Article
import com.example.bankmandirimobileintern.domain.manager.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(private val newsApi: NewsApi): NewsRepository {

    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(newsApi = newsApi, sources = sources.joinToString(separator = ","))
            }
        ).flow
    }

//    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
//        return Pager(
//            config = PagingConfig(pageSize = 10),
//            pagingSourceFactory = {
//                SearchNewsPagingSource(
//                    api = newsApi,
//                    searchQuery = searchQuery,
//                    sources = sources.joinToString(separator = ",")
//                )
//            }
//        ).flow
//    }


}