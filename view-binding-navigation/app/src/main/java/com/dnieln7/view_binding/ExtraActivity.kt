package com.dnieln7.view_binding

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dnieln7.view_binding.databinding.ActivityExtraBinding

class ExtraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExtraBinding

    private var changed = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExtraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        binding.changeText.setOnClickListener {
            changed++

            binding.dynamicSubtitle.text = "This text has been changed $changed times"
            binding.dynamicBody.text = "Now you can exit this activity"
            binding.exit.visibility = View.VISIBLE
        }

        binding.exit.setOnClickListener { finish() }
    }
}