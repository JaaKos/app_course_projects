package com.example.sensormanagerapp

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SensorViewModel : ViewModel(), SensorEventListener {
    private var sensorManager: SensorManager? = null
    private var accelerometer: Sensor? = null

    // StateFlow to hold the sensor readings
    private val _sensorData = MutableStateFlow(Triple(0f, 0f, 0f))
    val sensorData: StateFlow<Triple<Float, Float, Float>> = _sensorData

    // Initialize sensor manager and accelerometer
    fun initializeSensors(sensorManager: SensorManager) {
        this.sensorManager = sensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        // todella outo juttu, mutta jostain syystä puhelimeni gyroskooppi ja acceleration meter
        // tekevät toistensa työt.
    }

    // Start listening to sensor
    fun startMonitoring() {
        accelerometer?.let {
            sensorManager?.registerListener(
                this,
                it,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    // Stop listening to sensor
    fun stopMonitoring() {
        sensorManager?.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            if (it.sensor.type == Sensor.TYPE_GYROSCOPE) {
                _sensorData.value = Triple(
                    it.values[0], // X axis
                    it.values[1], // Y axis
                    it.values[2]  // Z axis
                )
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Handle accuracy changes if needed
    }

    override fun onCleared() {
        super.onCleared()
        stopMonitoring()
    }
}
