package com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.news

import com.example.bankmandirimobileintern.data.local.NewsDao
import com.example.bankmandirimobileintern.domain.manager.model.Article
import javax.inject.Inject


class UpsertArticle @Inject constructor(
    private val newsDao: NewsDao
) {
    suspend operator fun invoke(article: Article) {
        newsDao.upsert(article = article)
    }
}
