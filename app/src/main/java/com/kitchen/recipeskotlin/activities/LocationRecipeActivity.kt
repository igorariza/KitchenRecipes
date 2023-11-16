package com.kitchen.recipeskotlin.activities

import android.app.Activity
import android.app.ProgressDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.kitchen.recipeskotlin.R
import com.kitchen.recipeskotlin.databinding.ActivityLocationRecipeBinding

@Suppress("DEPRECATION")
class LocationRecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLocationRecipeBinding
    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationRecipeBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        createMapFragment()
        val strArea = intent.getStringExtra("strArea")
        binding.tvSubTitle!!.text = strArea
        binding.tvTitle.text = "Location"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }

        binding.toolbarLocation.title = null
        setSupportActionBar(binding.toolbarLocation)
        assert(supportActionBar != null)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.toolbarLocation.setNavigationOnClickListener { onBackPressed() }

        progressDialog = ProgressDialog(this)
        progressDialog!!.setTitle("Please Wait")
        progressDialog!!.setMessage("Displaying data ...")
        progressDialog!!.setCancelable(false)
        progressDialog!!.setCanceledOnTouchOutside(false)
    }

    companion object {
        //Set Transparent Status bar
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val win = activity.window
            val winParams = win.attributes
            if (on) {
                winParams.flags = winParams.flags or bits
            } else {
                winParams.flags = winParams.flags and bits.inv()
            }
            win.attributes = winParams
        }
    }

    private fun createMapFragment() {

        var arrayCountriesLatLngRandom = arrayOfNulls<com.google.android.gms.maps.model.LatLng>(10)
        for (i in 0..9) {
            arrayCountriesLatLngRandom[i] = com.google.android.gms.maps.model.LatLng(
                //LAtin America
                (0..90).random().toDouble(),
                (0..90).random().toDouble(),
            )
        }
        var number = (0..9).random()
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentMap) as com.google.android.gms.maps.SupportMapFragment?
        mapFragment!!.getMapAsync { googleMap ->
            googleMap.uiSettings.isZoomControlsEnabled = true
            googleMap.uiSettings.isCompassEnabled = true
            googleMap.uiSettings.isMyLocationButtonEnabled = true
            googleMap.uiSettings.isMapToolbarEnabled = true
            googleMap.addMarker(
                com.google.android.gms.maps.model.MarkerOptions()
                    .position(com.google.android.gms.maps.model.LatLng(arrayCountriesLatLngRandom[number]!!.latitude, arrayCountriesLatLngRandom[number]!!.longitude))
            )
            googleMap.animateCamera(
                com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(
                    com.google.android.gms.maps.model.LatLng(arrayCountriesLatLngRandom[number]!!.latitude, arrayCountriesLatLngRandom[number]!!.longitude), 1f
                )
            )
        }
    }
}