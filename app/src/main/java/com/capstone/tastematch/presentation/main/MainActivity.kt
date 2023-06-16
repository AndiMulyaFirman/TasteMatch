package com.capstone.tastematch.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.capstone.tastematch.R
import com.capstone.tastematch.presentation.add.AddDetectionMenuActivity
import com.capstone.tastematch.presentation.home.HomeFragment
import com.capstone.tastematch.presentation.profile.ProfileFragment
import com.capstone.tastematch.presentation.search.SearchFragment
import com.capstone.tastematch.presentation.settings.SettingsActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var fabButton: FloatingActionButton

    override fun onBackPressed() {
        // Do nothing to disable the back button
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        fabButton = findViewById(R.id.fab)

        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(2).isEnabled = false

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.miHome -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.miSearch -> {
                    replaceFragment(SearchFragment())
                    true
                }
                R.id.miProfile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                R.id.miSettings -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }

        fabButton.setOnClickListener {
            val intent = Intent(this, AddDetectionMenuActivity::class.java)
            startActivity(intent)        }

        // Set the default selected menu item
        bottomNavigationView.selectedItemId = R.id.miHome
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
