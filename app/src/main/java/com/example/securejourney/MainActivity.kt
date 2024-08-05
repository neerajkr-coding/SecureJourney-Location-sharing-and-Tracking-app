package com.example.securejourney

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigationBar = findViewById<BottomNavigationView>(R.id.navigationBar)

        navigationBar.setOnItemSelectedListener { menuItem->

            if(menuItem.itemId == R.id.nav_guard){
                inflateFragment(GuardFragment.newInstance());
            }else if(menuItem.itemId == R.id.nav_home){
                inflateFragment(HomeFragment.newInstance())
            }else if(menuItem.itemId == R.id.nav_dashboard){
                inflateFragment(DashboardFragment.newInstance())
            }else{
                inflateFragment(ProfileFragment.newInstance())
            }
            true
        }

            navigationBar.selectedItemId = R.id.nav_home;
    }

    private fun inflateFragment(fr: Fragment){

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fr)
        transaction.commit()
    }

    }
