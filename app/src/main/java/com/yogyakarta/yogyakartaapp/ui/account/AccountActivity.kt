package com.yogyakarta.yogyakartaapp.ui.account

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.yogyakarta.yogyakartaapp.R
import com.yogyakarta.yogyakartaapp.base.BaseActivity
import com.yogyakarta.yogyakartaapp.fixtures.ACCOUNT_PERMISSION_REQUEST_CODE
import com.yogyakarta.yogyakartaapp.fixtures.PermissionStatus
import com.yogyakarta.yogyakartaapp.vo.Permission
import kotlinx.android.synthetic.main.activity_account.*
import javax.inject.Inject

class AccountActivity : BaseActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var accountViewModel: AccountViewModel? = null

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.let { mMap = it }
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            setupMap(location)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        supportActionBar.apply {
            title = getString(R.string.account_title)
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        accountViewModel =
            ViewModelProviders.of(this, viewModelFactory)[AccountViewModel::class.java]

        accountViewModel?.getPermission()?.observe(this, Observer { permission ->
            when (permission.status) {
                PermissionStatus.GRANTED -> {
                    if (textView_account_grantPermission.visibility == View.VISIBLE) {
                        textView_account_grantPermission.visibility = View.GONE
                    }
                    val mapFragment =
                        supportFragmentManager.findFragmentById(R.id.map_account_location) as SupportMapFragment
                    mapFragment.getMapAsync(this)

                }
                PermissionStatus.DENY -> {
                    if (textView_account_grantPermission.visibility == View.INVISIBLE) {
                        requestLocationPermission()
                    }
                }
                PermissionStatus.BLOCKED -> {
                    Toast.makeText(
                        this,
                        getString(R.string.account_message_permission_blocked),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

        textView_account_grantPermission.setOnClickListener {
            if (accountViewModel?.getPermission()?.value?.status == PermissionStatus.BLOCKED) {
                Toast.makeText(
                    this,
                    getString(R.string.account_message_permission_blocked),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                requestLocationPermission()
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            ACCOUNT_PERMISSION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    accountViewModel?.setPermission(Permission.granted())
                } else {
                    textView_account_grantPermission.visibility = View.VISIBLE
                    accountViewModel?.setPermission(Permission.denied())
                }
            }
        }
    }


    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            ACCOUNT_PERMISSION_REQUEST_CODE
        )
    }

    private fun setupMap(location: Location?) {
        var latLng: LatLng? = null
        location?.let { latLng = LatLng(it.latitude, it.longitude) }
        mMap.addMarker(
            MarkerOptions()
                .position(latLng!!)
                .title(getString(R.string.account_my_location))
                .icon(BitmapDescriptorFactory.defaultMarker())
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, mMap.maxZoomLevel - 8))
    }

}
