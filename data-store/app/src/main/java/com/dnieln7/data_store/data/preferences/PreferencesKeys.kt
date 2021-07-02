package com.dnieln7.data_store.data.preferences

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val OWNER_NAME = stringPreferencesKey("owner_name")
    val OWNER_AGE = intPreferencesKey("owner_age")
}