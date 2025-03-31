package com.example.bankmandirimobileintern.presentation.details

import dagger.hilt.android.lifecycle.HiltViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankmandirimobileintern.domain.manager.model.Article
import com.example.bankmandirimobileintern.util.UIComponent
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.State
import com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.news.DeleteArticle
import com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.news.GetSavedArticle
import com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.news.UpsertArticle


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getSavedArticleUseCase: GetSavedArticle,
    private val deleteArticleUseCase: DeleteArticle,
    private val upsertArticleUseCase: UpsertArticle
) : ViewModel() {

    private var _sideEffect = mutableStateOf<UIComponent?>(null)
    val sideEffect: State<UIComponent?> = _sideEffect

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = getSavedArticleUseCase(url = event.article.url)
                    if (article == null){
                        upsertArticle(article = event.article)
                    }else{
                        deleteArticle(article = event.article)
                    }
                }
            }
            is DetailsEvent.RemoveSideEffect -> {
                _sideEffect.value = null
            }
        }
    }

    private suspend fun deleteArticle(article: Article) {
        deleteArticleUseCase(article = article)
        _sideEffect.value = UIComponent.Toast("Article deleted")
    }

    private suspend fun upsertArticle(article: Article) {
        upsertArticleUseCase(article = article)
        _sideEffect.value = UIComponent.Toast("Article Inserted")
    }
}