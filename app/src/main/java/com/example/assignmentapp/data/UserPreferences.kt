package com.example.assignmentapp.data
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

class UserPreferences(
    context: Context
) {
    private val applicationContext = context.applicationContext
    private val KEY_AUTH = stringPreferencesKey("key_auth")

    val authToken: Flow<String?>
        get() = applicationContext.dataStore.data.map {preferences ->
            preferences[KEY_AUTH] ?:"Token not found"
        }

    suspend fun saveAuthToken(authToken: String) {
        applicationContext.dataStore.edit {preferences ->
            preferences[KEY_AUTH] = authToken
        }
    }







}

