package com.example.bankmandirimobileintern.presentation.details

import dagger.hilt.android.lifecycle.HiltViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankmandirimobileintern.domain.manager.model.Article
import com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.news.NewsUseCases
import com.example.bankmandirimobileintern.util.UIComponent
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.State


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    private var _sideEffect = mutableStateOf<UIComponent?>(null)
    val sideEffect: State<UIComponent?> = _sideEffect

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val existingArticle = newsUseCases.getArticle(url = event.article.url)
                    if (existingArticle == null) {
                        upsertArticle(article = event.article)
                    } else {
                        deleteArticle(article = existingArticle)
                    }
                }
            }
            is DetailsEvent.RemoveSideEffect -> {
                // Gunakan .value untuk mengubah nilai
                _sideEffect.value = null
            }
        }
    }

    private suspend fun deleteArticle(article: Article) {
        newsUseCases.deleteArticle(article = article)
        _sideEffect.value = UIComponent.Toast("Article deleted")
    }

    private suspend fun upsertArticle(article: Article) {
        newsUseCases.upsertArticle(article = article)
        _sideEffect.value = UIComponent.Toast("Article Inserted")
    }
}