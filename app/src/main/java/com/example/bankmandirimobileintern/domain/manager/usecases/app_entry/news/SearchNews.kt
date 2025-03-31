package com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.news

import androidx.paging.PagingData
import com.example.bankmandirimobileintern.domain.manager.model.Article
import com.example.bankmandirimobileintern.domain.manager.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNews @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(
            searchQuery = searchQuery,
            sources = sources
        )
    }
}