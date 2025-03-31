package com.example.bankmandirimobileintern.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateUtils {
    fun formatPublishedDate(dateString: String?): String {
        // Periksa apakah dateString null atau kosong
        if (dateString.isNullOrBlank()) {
            return "" // Atau return "No date available" sesuai kebutuhan
        }

        try {
            // Cek apakah string memiliki format timestamp
            if (dateString.contains("T")) {
                // Format input: 2025-03-29T19:02:46Z
                val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
                inputFormat.timeZone = TimeZone.getTimeZone("UTC")
                val date = inputFormat.parse(dateString) ?: return dateString

                // Format output dengan tanggal, bulan, dan tahun
                val outputFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.ENGLISH)
                outputFormat.timeZone = TimeZone.getDefault()

                return outputFormat.format(date)
            }
            return dateString
        } catch (e: Exception) {
            // Jika error, kembalikan string kosong atau pesan default
            return ""
        }
    }
}