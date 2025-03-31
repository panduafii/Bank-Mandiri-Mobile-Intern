package com.example.bankmandirimobileintern.presentation.bookmark

import com.example.bankmandirimobileintern.domain.manager.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)