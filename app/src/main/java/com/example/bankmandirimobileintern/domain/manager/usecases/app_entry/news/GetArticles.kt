package com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.news

import com.example.bankmandirimobileintern.data.local.NewsDao
import com.example.bankmandirimobileintern.domain.manager.model.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetSavedArticles @Inject constructor(
    private val newsDao: NewsDao
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

}