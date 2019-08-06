package com.adryanev.dicoding.mycontacts.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.adryanev.dicoding.mycontacts.R
import com.adryanev.dicoding.mycontacts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var  appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        navController = findNavController(R.id.main_nav_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        // Set up ActionBar
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)





    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}
