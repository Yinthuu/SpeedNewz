package com.group3.speednewz

import android.annotation.SuppressLint
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.group3.speednewz.ui.login.LoginActivity
import android.content.Context
import android.util.Log
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import com.group3.speednewz.models.NewsData


object UserSession {
    var isLoggedIn = false
    var username = ""
}

class MainActivity( ) : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    //var db: SQLiteDatabase? = null

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val isLoggedIn = getSharedPreferences("prefs", Context.MODE_PRIVATE).getBoolean("isLoggedIn", false)
        //Creating the database 'SpeedyNewz'
        var db: SQLiteDatabase?  = openOrCreateDatabase("SpeedyNewz", Context.MODE_PRIVATE, null)

        //Creating table 'users' if it does not exist with the fields userid, userName, password.
        db?.execSQL("CREATE TABLE IF NOT EXISTS users (userId VARCHAR, userName VARCHAR, password VARCHAR)")
        // clear the previous data from the users table
        db?.execSQL("DELETE FROM users")
        //inserts records into table
        db?.execSQL("INSERT INTO users(userId, userName, password) VALUES ('001','admin','123456')");


        //Creating table 'news' if it does not exist with the fields imageURL, title, content, favorites.
        db?.execSQL("CREATE TABLE IF NOT EXISTS news (imageURL VARCHAR, title VARCHAR, content VARCHAR, favorites INTEGER)")

        // clear the previous data from the news table
        db?.execSQL("DELETE FROM news")

//create a new news item and insert it into the table
        val imageURL1 = "https://cdn2.thecatapi.com/images/DBmIBhhyv.jpg"
        val title1 = "Breaking News!"
        val content1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus placerat, ligula at efficitur blandit, turpis quam tristique erat, a suscipit lectus ex eget mauris."
        val isFavorite1 = true
        val favorites1 = if (isFavorite1) 1 else 0 // convert Boolean to INTEGER

        db?.execSQL("INSERT INTO news(imageURL, title, content, favorites) VALUES ('$imageURL1', '$title1', '$content1', $favorites1)")

//create a new news item and insert it into the table
        val imageURL2 = "https://cdn2.thecatapi.com/images/KJF8fB_20.jpg"
        val title2 = "Technology Update"
        val content2 = "Nulla facilisi. Curabitur semper purus non tortor pharetra bibendum. Sed et malesuada nunc. Integer id enim sit amet est auctor malesuada vitae vel velit."
        val isFavorite2 = true
        val favorites2 = if (isFavorite2) 1 else 0 // convert Boolean to INTEGER

        db?.execSQL("INSERT INTO news(imageURL, title, content, favorites) VALUES ('$imageURL2', '$title2', '$content2', $favorites2)")

//create a new news item and insert it into the table
        val imageURL3 = "https://cdn2.thecatapi.com/images/vJB8rwfdX.jpg"
        val title3 = "Weather Update"
        val content3 = "Fusce aliquam mauris euismod, fringilla justo at, dignissim enim. Suspendisse auctor metus vel lorem bibendum euismod. Morbi nec velit massa."
        val isFavorite3 = false
        val favorites3 = if (isFavorite3) 1 else 0 // convert Boolean to INTEGER

        db?.execSQL("INSERT INTO news(imageURL, title, content, favorites) VALUES ('$imageURL3', '$title3', '$content3', $favorites3)")

//create another news item and insert it into the table
        val imageURL4 = "https://cdn2.thecatapi.com/images/KJF8fB_20.jpg"
        val title4 = "Sports Update"
        val content4 = "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Sed at volutpat tellus. Aliquam malesuada tellus ac malesuada scelerisque."
        val isFavorite4 = false
        val favorites4 = if (isFavorite4) 1 else 0 // convert Boolean to INTEGER

        db?.execSQL("INSERT INTO news(imageURL, title, content, favorites) VALUES ('$imageURL4', '$title4', '$content4', $favorites4)")




// select all columns from the news table
        val query = "SELECT * FROM news"

// execute the query and get a cursor
        val cursor = db?.rawQuery(query, null)

// create a list of news items to hold the data
        val newsItems = mutableListOf<NewsData>()


//Pass the list of news items to the HomeFragment.kt using a bundle.
        val bundle = Bundle()
        bundle.putSerializable("newsItems", ArrayList(newsItems))
        val homeFragment = HomeFragment()
        homeFragment.arguments = bundle

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