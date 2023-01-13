package com.udacity.shoestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // !!!! Must Delete before DataBindingUtil.setContentView(
        //setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        toolbar = binding.mytoolbar

        // using toolbar as ActionBar
        setSupportActionBar(toolbar)

        // Display application icon in the toolbar
//        supportActionBar!!.setDisplayShowHomeEnabled(true)
//        supportActionBar!!.setLogo(R.drawable.shoe)
//        supportActionBar!!.setDisplayUseLogoEnabled(true)

        val navController = this.findNavController(R.id.my_nav_host_fragment)

        // Config the AppBar Buttons (Up Button not appear on the start destination of the graph)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        // Change Icons and Title of ActionBar Automatically with fragment label
        NavigationUI.setupActionBarWithNavController(this, navController,appBarConfiguration)

        // set my toolbar with the Fragments Title and Navigation Button
        NavigationUI.setupWithNavController(toolbar,navController,appBarConfiguration)
    }

    // bug : at shoe list screen when i press (toolbar up button) : will nav to login screen
    // and when i press (back button of the mobile) : will exit the app
    override fun onSupportNavigateUp(): Boolean {
//        return super.onSupportNavigateUp()
        val navController = this.findNavController(R.id.my_nav_host_fragment)
        return NavigationUI.navigateUp(navController,appBarConfiguration)
    }

}
















