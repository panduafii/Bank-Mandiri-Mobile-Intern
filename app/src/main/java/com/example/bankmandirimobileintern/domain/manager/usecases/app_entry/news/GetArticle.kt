package com.example.bankmandirimobileintern.domain.manager.usecases.app_entry.news

import com.example.bankmandirimobileintern.data.local.NewsDao
import com.example.bankmandirimobileintern.domain.manager.model.Article

class GetArticle (
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(url: String): Article?{
        return newsDao.getArticle(url = url)
    }

}