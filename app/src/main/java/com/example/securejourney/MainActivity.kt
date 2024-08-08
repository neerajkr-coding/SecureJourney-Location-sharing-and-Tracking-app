package com.example.securejourney

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.Manifest

class MainActivity : AppCompatActivity() {

    val permission = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    val locationPermissionCode = 7

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestUsersPermession()

        val navigationBar = findViewById<BottomNavigationView>(R.id.navigationBar)

        navigationBar.setOnItemSelectedListener { menuItem->

            if(menuItem.itemId == R.id.nav_guard){
                inflateFragment(GuardFragment.newInstance());
            }else if(menuItem.itemId == R.id.nav_home){
                inflateFragment(HomeFragment())
            }else if(menuItem.itemId == R.id.nav_dashboard){
                inflateFragment(MapsFragment())
            }else{
                inflateFragment(ProfileFragment.newInstance())
            }
            true
        }

            navigationBar.selectedItemId = R.id.nav_home;
    }

    private fun requestUsersPermession() {

        ActivityCompat.requestPermissions(this, permission,locationPermissionCode)
    }

    private fun inflateFragment(fr: Fragment){

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fr)
        transaction.commit()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    }
