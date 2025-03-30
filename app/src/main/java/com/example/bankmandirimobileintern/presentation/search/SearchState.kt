package com.example.bankmandirimobileintern.presentation.search

import androidx.paging.PagingData
import com.example.bankmandirimobileintern.domain.manager.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)