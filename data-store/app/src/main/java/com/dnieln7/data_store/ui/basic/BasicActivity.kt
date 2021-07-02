package com.dnieln7.data_store.ui.basic

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.dnieln7.data_store.R
import com.dnieln7.data_store.data.preferences.PreferencesManager
import com.dnieln7.data_store.ui.proto.ProtoActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView

class BasicActivity : AppCompatActivity() {

    private lateinit var manager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = PreferencesManager(this)

        init()
    }

    private fun init() {
        manager.ownerName.asLiveData().observe(this, { name ->
            findViewById<MaterialTextView>(R.id.display_name).text = "Owner name: $name"
        })
        manager.ownerAge.asLiveData().observe(this, { age ->
            findViewById<MaterialTextView>(R.id.display_age).text = "Owner age: $age"
        })
    }

    fun save(view: View) {
        val name = findViewById<TextInputEditText>(R.id.input_name).text.toString()
        val age = findViewById<TextInputEditText>(R.id.input_age).text.toString()

        lifecycleScope.launchWhenCreated {
            if (name.isNotBlank()) {
                manager.changeOwnerName(name)
            }

            if (age.isNotBlank()) {
                manager.changeOwnerAge(age.trim().toInt())
            }
        }
    }

    fun openProtoStore(view: View) {
        startActivity(Intent(this, ProtoActivity::class.java))
    }
}