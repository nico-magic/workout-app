package com.example.workoutapprebornkotlin

import android.database.CursorWindow
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.reflect.Field


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment_main)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.landingFragment, R.id.showExercisesFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

        bottomNavigationView.setupWithNavController(navController)
        try {
            val field: Field = CursorWindow::class.java.getDeclaredField("sCursorWindowSize")
            field.setAccessible(true)
            field.set(null, 100 * 1024 * 1024) //the 100MB is the new size
        } catch (e: Exception) {
            e.printStackTrace()
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.startWorkoutFragment) {

                bottomNavigationView.visibility = View.GONE
            } else {

                bottomNavigationView.visibility = View.VISIBLE
            }
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}