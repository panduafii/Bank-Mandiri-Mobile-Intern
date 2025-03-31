package com.example.bankmandirimobileintern.data.remote.dto

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.bankmandirimobileintern.domain.manager.model.Article
import kotlinx.coroutines.delay

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val sources: String
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newsResponse = newsApi.getNews(sources = sources, page = page)

            delay(10000)
            // Cetak seluruh artikel untuk investigasi
            newsResponse.articles.forEach { article ->
                Log.d("NewsAPI", "Title: ${article.title}")
            }

            val filteredArticles = newsResponse.articles
                .filterNot { article ->
                    article.title?.contains("The latest five minute news bulletin from BBC World Service", ignoreCase = true) == true ||
                            article.title?.contains("latest news bulletin", ignoreCase = true) == true
                }
                .distinctBy { it.title }

            LoadResult.Page(
                data = filteredArticles,
                nextKey = if (filteredArticles.isEmpty()) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(throwable = e)
        }
    }
}