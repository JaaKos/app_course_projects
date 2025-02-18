package com.example.locationapp

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


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
fun App(viewModel: LocationViewModel = viewModel()) {
    val context = LocalContext.current
    val locationCoords by viewModel.locationState.collectAsState()
    val locationText = "%.2f".format(locationCoords.first)  + " " + "%.2f".format(locationCoords.second)
    var isMonitoring by remember { mutableStateOf(false) }

    val requestPermissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission(),
            onResult = {
                isGranted ->
                if (isGranted) {
                    viewModel.startLocationUpdates()
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
            isMonitoring = !isMonitoring
            if (isMonitoring) {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            } else {
                viewModel.stopLocationUpdates()
            }

        }) {
            Text(if (isMonitoring) "Lopeta" else "Aloita")
        }

        Button(onClick = {openMaps(context, latitude = locationCoords.first, longitude = locationCoords.second)}, Modifier.align(Alignment.CenterHorizontally)) { Text("Show map location") }
    }
}

fun openMaps(context: Context, latitude: Double, longitude: Double) {
    val locationUri = Uri.parse("geo:$latitude,$longitude")
    val intent = Intent(Intent.ACTION_VIEW, locationUri)

    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}