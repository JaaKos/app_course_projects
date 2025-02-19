package com.example.locationapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.mutableStateOf

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                val isAirplaneModeOn = intent.getBooleanExtra("state", false)
                AirplaneModeManager.isAirplaneModeEnabled.value = isAirplaneModeOn
            }
        }
    }
}

object AirplaneModeManager {
    var isAirplaneModeEnabled = mutableStateOf(false)
}