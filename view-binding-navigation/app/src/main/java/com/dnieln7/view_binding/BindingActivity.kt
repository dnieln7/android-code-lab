package com.dnieln7.view_binding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController


class BindingActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binding)
        setSupportActionBar(findViewById(R.id.binding_toolbar))

        val host = supportFragmentManager.findFragmentById(R.id.binding_nav_host) as NavHostFragment
        val controller = host.navController

        appBarConfiguration = AppBarConfiguration(controller.graph)

        setupActionBarWithNavController(controller, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.binding_nav_host).navigateUp(appBarConfiguration)
    }
}