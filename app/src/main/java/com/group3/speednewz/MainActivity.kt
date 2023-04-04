package com.group3.speednewz

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.group3.speednewz.data.LoginRepository
import com.group3.speednewz.ui.login.LoginActivity
import com.group3.speednewz.ui.login.LoginViewModel

object UserSession {
    var isLoggedIn = false
    var username = ""
}

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val isLoggedIn = getSharedPreferences("prefs", Context.MODE_PRIVATE).getBoolean("isLoggedIn", false)
        if (UserSession.isLoggedIn) {
            setContentView(R.layout.activity_main)
            setSupportActionBar(findViewById(R.id.toolbar))
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            //Creating top level destinations
            //and adding them to the draw
            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.nav_home, R.id.nav_recent,
                    R.id.nav_favorites
                ), findViewById(R.id.drawer_layout)
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            findViewById<NavigationView>(R.id.nav_view)?.setupWithNavController(navController)
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }
    //Show drawer when clicking left three STRIKE menu button
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    //Showing three DOTS menu on the right
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.activity_main_drawer, menu)
//        return true
//    }

    //When clicking an option on the pop up after clicking three DOTS, it redirects to the respective page
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
//    }
}