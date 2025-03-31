package com.example.bankmandirimobileintern.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bankmandirimobileintern.domain.manager.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long  // Mengembalikan ID yang di-upsert

    @Delete
    suspend fun delete(article: Article): Int  // Mengembalikan jumlah baris yang dihapus

    @Query("SELECT * FROM Article")
    fun getArticles(): Flow<List<Article>>
}
