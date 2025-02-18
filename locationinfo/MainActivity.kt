package com.example.locationapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    val context = LocalContext.current
    var locationText by remember {mutableStateOf("")}
    val fusedLocationClient: FusedLocationProviderClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val requestPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = {
                isGranted ->
                if (isGranted) {
                    getLastKnownLocation(context, fusedLocationClient) { location ->

                        locationText = location
                    }
                } else {
                    Toast.makeText(context, "ERROR: Lupa estetty", Toast.LENGTH_SHORT).show()
                }
            }
        )
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = locationText, modifier = Modifier.padding(bottom = 16.dp))
        Button(onClick = {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
            } else {
                getLastKnownLocation(context, fusedLocationClient) { location ->
                    locationText = location
                }
            }
        }) {
            Text("Paikanna")
        }
    }
}

@SuppressLint("MissingPermission")
fun getLastKnownLocation(
    context: android.content.Context,
    fusedLocationClient: FusedLocationProviderClient,
    onLocationRetrieved: (String) -> Unit
) {
    fusedLocationClient.lastLocation.addOnSuccessListener { value ->
        if (value != null) {
            val lat = "%.2f".format(value.latitude);
            val long = "%.2f".format(value.longitude)
            onLocationRetrieved("Lat: $lat, Lng: $long")
        } else {
            Toast.makeText(context, "ERROR: paikkaa ei voitu määrittää", Toast.LENGTH_SHORT).show()
        }
    }
}