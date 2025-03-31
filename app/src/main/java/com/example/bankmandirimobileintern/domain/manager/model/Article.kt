package com.example.bankmandirimobileintern.domain.manager.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Article(
    val author: String? = null, // Nullable dengan default null
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val source: Source,
    val title: String? = null,
    @PrimaryKey val url: String,
    val urlToImage: String? = null
): Parcelable