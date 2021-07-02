package com.dnieln7.data_binding

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dnieln7.data_binding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val catViewModel: CatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.catViewModel = catViewModel
        binding.lifecycleOwner = this
    }
}