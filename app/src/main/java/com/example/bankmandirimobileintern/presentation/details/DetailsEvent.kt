package com.example.bankmandirimobileintern.presentation.details

import com.example.bankmandirimobileintern.domain.manager.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()

}