package com.dnieln7.data_store.data.preferences

import android.content.Context
import android.content.ContextWrapper
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.dnieln7.data_store.data.model.PetProto
import com.dnieln7.data_store.data.preferences.PreferencesUtils.createDataStore
import com.dnieln7.data_store.data.preferences.PreferencesUtils.createProtoDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class PreferencesManager(context: Context) : ContextWrapper(context) {
    private val dataStore by lazy {
        createDataStore
    }

    private val protoDataStore by lazy {
        createProtoDataStore
    }

    val petFlow = protoDataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(PetProto.getDefaultInstance())
        } else {
            throw exception
        }
    }

    suspend fun changePetName(name: String) {
        protoDataStore.updateData { petProto ->
            petProto.toBuilder().setName(name).build()
        }
    }

    suspend fun changePetAge(name: Int) {
        protoDataStore.updateData { petProto ->
            petProto.toBuilder().setAge(name).build()
        }
    }

    val ownerName = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences -> preferences[PreferencesKeys.OWNER_NAME] ?: "" }

    val ownerAge = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences -> preferences[PreferencesKeys.OWNER_AGE] ?: 0 }

    suspend fun changeOwnerName(name: String) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[PreferencesKeys.OWNER_NAME] = name
        }
    }

    suspend fun changeOwnerAge(age: Int) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[PreferencesKeys.OWNER_AGE] = age
        }
    }
}