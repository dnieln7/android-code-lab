package com.dnieln7.data_store.ui.proto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.dnieln7.data_store.R
import com.dnieln7.data_store.data.preferences.PreferencesManager
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView

class ProtoActivity : AppCompatActivity() {
    private lateinit var manager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proto)

        manager = PreferencesManager(this)

        init()
    }

    private fun init() {
        manager.petFlow.asLiveData().observe(this, { petProto ->
            findViewById<MaterialTextView>(R.id.display_name).text = "Pet name: ${petProto.name}"
            findViewById<MaterialTextView>(R.id.display_age).text = "Pet age: ${petProto.age}"
        })
    }

    fun save(view: View) {
        val name = findViewById<TextInputEditText>(R.id.input_name).text.toString()
        val age = findViewById<TextInputEditText>(R.id.input_age).text.toString()

        lifecycleScope.launchWhenCreated {
            if (name.isNotBlank()) {
                manager.changePetName(name)
            }

            if (age.isNotBlank()) {
                manager.changePetAge(age.trim().toInt())
            }
        }
    }

    fun closeProtoStore(view: View) {
        finish()
    }
}