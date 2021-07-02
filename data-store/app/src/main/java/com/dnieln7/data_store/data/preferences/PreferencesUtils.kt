package com.dnieln7.data_store.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.migrations.SharedPreferencesMigration
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.preferencesDataStore
import com.dnieln7.data_store.data.model.PetProto
import com.dnieln7.data_store.data.preferences.proto.serializer.PetSerializer

object PreferencesUtils {
    private const val PREFERENCES_NAME = "app_preferences"
    private const val PET_PREFERENCES_NAME = "pet_preferences"
    private const val PET_PREFERENCES_FILE_NAME = "pet_preferences.pb"

    val Context.createDataStoreWithMigrations by preferencesDataStore(
        name = PREFERENCES_NAME,
        produceMigrations = { ctx ->
            listOf(SharedPreferencesMigration(ctx, PREFERENCES_NAME))
        }
    )

    val Context.createDataStore by preferencesDataStore(
        name = PREFERENCES_NAME,
    )


    val Context.createProtoDataStoreWithMigrations: DataStore<PetProto> by dataStore(
        fileName = PET_PREFERENCES_FILE_NAME,
        serializer = PetSerializer(),
        produceMigrations = { ctx ->
            listOf(
                SharedPreferencesMigration(
                    ctx,
                    PET_PREFERENCES_NAME
                ) { sharedPreferencesView, petProto ->

                    petProto.toBuilder()
                        .setName(sharedPreferencesView.getString("PET_NAME", ""))
                        .setAge(sharedPreferencesView.getInt("PET_AGE", 0))
                        .build()
                }
            )
        }
    )

    val Context.createProtoDataStore: DataStore<PetProto> by dataStore(
        fileName = PET_PREFERENCES_FILE_NAME,
        serializer = PetSerializer(),
    )
}