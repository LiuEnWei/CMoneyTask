package com.wayne.cmoneytask.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.wayne.cmoneytask.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var appBarConfig: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appBarConfig = AppBarConfiguration(setOf(R.id.homeFragment))
        toolbar.setupWithNavController(findNavController(), appBarConfig)
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(findNavController(), appBarConfig)

    }

    private fun findNavController(): NavController {
        return Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController().navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }
}