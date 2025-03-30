package com.example.bankmandirimobileintern.data.remote.dto

import com.example.bankmandirimobileintern.domain.manager.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)