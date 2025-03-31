package com.example.bankmandirimobileintern.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.bankmandirimobileintern.domain.manager.model.Article
import com.example.bankmandirimobileintern.presentation.Dimens.BigPadding1
import com.example.bankmandirimobileintern.presentation.Dimens.MediumPadding1
import com.example.bankmandirimobileintern.presentation.Dimens.MediumPadding2
import com.example.bankmandirimobileintern.presentation.Dimens.MediumPadding3
import com.example.bankmandirimobileintern.presentation.common.ArticlesList
import com.example.bankmandirimobileintern.presentation.common.SearchBar

@Composable
fun SearchScreen(
    state: SearchState,
    event:(SearchEvent) -> Unit,
    navigateToDetails:(Article) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .padding(top = BigPadding1, start = MediumPadding1, end = MediumPadding1)
    ) {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            text = state.searchQuery,
            readOnly = false,
            onValueChange = {
                event(SearchEvent.UpdateSearchQuery(it))
            },
            onSearch = {
                keyboardController?.hide()
                event(SearchEvent.SearchNews)
            },
            onClick = null
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(
                articles = articles,
//                onClick = {
//                    //TODO: Navigate to details screen
//                }
                onClick = navigateToDetails
            )
        }
    }
}