package com.example.bankmandirimobileintern.data.manager

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.bankmandirimobileintern.domain.manager.LocalUserManager
import com.example.bankmandirimobileintern.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.Preferences
import javax.inject.Inject

// Membaca dan Menyimpan Preferences menggunakan DataStore
class LocalUserManagerImpl @Inject constructor(
    private val application: Application
) : LocalUserManager {

    // Fungsi untuk menyimpan data (contoh: menyimpan nilai boolean untuk APP_ENTRY)
    override suspend fun saveAppEntry() {
        application.dataStore.edit { settings ->
            settings[PreferenceKeys.APP_ENTRY] = true // Menyimpan nilai true untuk APP_ENTRY
        }
    }

    // Fungsi untuk membaca nilai boolean dari DataStore
    override fun readAppEntry(): Flow<Boolean> {
        return application.dataStore.data.map { preferences ->
            preferences[PreferenceKeys.APP_ENTRY] ?: false // Mengambil nilai APP_ENTRY, default false
        }
    }
}

// Ekstensi untuk mengakses DataStore dari Context
private val readOnlyProperty = preferencesDataStore(name = Constants.USER_SETTINGS)

val Context.dataStore: DataStore<Preferences> by readOnlyProperty

// Definisikan kunci untuk menyimpan data di DataStore
private object PreferenceKeys {
    val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY)  // Kunci untuk nilai boolean
}
