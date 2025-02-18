package com.example.sensormanagerapp

import android.content.Context
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the sensor manager service
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        setContent {
            AccelerometerScreen(sensorManager)
        }
    }
}

@Composable
fun AccelerometerScreen(sensorManager: SensorManager) {
    val viewModel: SensorViewModel = viewModel()
    var isMonitoring by remember { mutableStateOf(false) }

    // Initialize sensors when the composable is first created
    LaunchedEffect(Unit) {
        viewModel.initializeSensors(sensorManager)
    }

    // Collect sensor data from StateFlow
    val sensorData by viewModel.sensorData.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                isMonitoring = !isMonitoring
                if (isMonitoring) {
                    viewModel.startMonitoring()
                } else {
                    viewModel.stopMonitoring()
                }
            }
        ) {
            Text(if (isMonitoring) "Stop Monitoring" else "Start Monitoring")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text("X-axis: %.2f".format(sensorData.first))
        Text("Y-axis: %.2f".format(sensorData.second))
        Text("Z-axis: %.2f".format(sensorData.third))
    }
}
