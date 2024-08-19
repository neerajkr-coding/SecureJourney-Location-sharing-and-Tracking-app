package com.example.securejourney

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private val permission = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
    )

    private val locationPermissionCode = 7


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestUsersPermession()



        if(!isLocationEnabled()){
            promptEnableLocation()
        }

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

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//        if(requestCode == locationPermissionCode){
//
//
//        }
//    }

//    private fun allPermissionGranted(): Boolean {
//
//        for(item in permission){
//            if(ContextCompat.checkSelfPermission(this, item) != PackageManager.PERMISSION_GRANTED){
//                return false;
//            }
//        }
//
//        return true;
//    }

    private fun promptEnableLocation() {
        AlertDialog.Builder(this)
            .setMessage("Location services are disabled. Please enable them to use this app.")
            .setPositiveButton("Location Settings" ,object : DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    val i = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivity(i)
                }
            })
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        return isGpsEnabled
    }


}
