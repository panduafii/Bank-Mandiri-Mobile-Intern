package com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.news

import com.example.bankmandirimobileintern.domain.manager.model.Article

class UpsertArticle(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article){
        newsDao.upsert(article = article)
    }

}