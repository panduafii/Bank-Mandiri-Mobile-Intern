package com.example.bankmandirimobileintern.presentation.home.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bankmandirimobileintern.R
import com.example.bankmandirimobileintern.domain.manager.model.Article
import com.example.bankmandirimobileintern.domain.manager.model.Source

import com.example.bankmandirimobileintern.presentation.Dimens.ArticleCardSize
import com.example.bankmandirimobileintern.presentation.Dimens.ExtraSmallPadding
import com.example.bankmandirimobileintern.presentation.Dimens.ExtraSmallPadding2
import com.example.bankmandirimobileintern.presentation.Dimens.SmallIconSize
import com.example.bankmandirimobileintern.ui.theme.BankMandiriMobileInternTheme
import com.example.bankmandirimobileintern.util.DateUtils


@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: (() -> Unit)? = null
) {
    val context = LocalContext.current
    Row(
        modifier = modifier
            .clickable { onClick?.invoke() }
            .padding(vertical = 8.dp) // Tambahkan padding vertikal
    ) {
        AsyncImage(
            modifier = Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp)) // Tambahkan jarak antar gambar dan teks

        Column(
            verticalArrangement = Arrangement.SpaceBetween, // Perbaiki layout vertikal
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding)
                .height(ArticleCardSize)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.text_title),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Source nama masih ditampilkan
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(0.4f, fill = false) // Kurangi weight-nya
                )

                Spacer(modifier = Modifier.width(ExtraSmallPadding2))

                // Tambahkan Row untuk icon waktu dan waktu
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(0.6f) // Berikan weight lebih besar untuk waktu
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_time),
                        contentDescription = null,
                        modifier = Modifier.size(SmallIconSize),
                        tint = colorResource(id = R.color.body)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = DateUtils.formatPublishedDate(article.publishedAt),
                        style = MaterialTheme.typography.labelSmall,
                        color = colorResource(id = R.color.body),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview() {
    BankMandiriMobileInternTheme (dynamicColor = false) {
        ArticleCard(
            article = Article(
                author = "",
                content = "",
                description = "",
                publishedAt = "2 hours",
                source = Source(id = "", name = "BBC"),
                title = "Her train broke down. Her phone died. And then she met her Saver in a",
                url = "",
                urlToImage = "https://ichef.bbci.co.uk/live-experience/cps/624/cpsprodpb/11787/production/_124395517_bbcbreakingnewsgraphic.jpg"
            )
        )
    }
}