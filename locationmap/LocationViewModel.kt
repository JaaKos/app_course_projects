package com.example.locationapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LocationViewModel(application: Application) : AndroidViewModel(application) {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)
    private val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 200).build()
    private val locationCallback: LocationCallback

    init {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let { updateLocation(it) }
            }
        }
    }

    private val _locationState = MutableStateFlow(Pair(0.0, 0.0))
    val locationState = _locationState.asStateFlow() // Read-only StateFlow

    @SuppressLint("MissingPermission")
    fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(getApplication(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            _locationState.value = Pair(0.0, 0.0)
            return
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private fun updateLocation(location: Location) {
        val lat = location.latitude
        val lng = location.longitude
        _locationState.value = Pair(lat, lng)
    }

    override fun onCleared() {
        super.onCleared()
        stopLocationUpdates()
    }
}