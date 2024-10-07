package com.example.commonintents

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.commonintents.ui.theme.CommonIntentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CommonIntentsTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val context = LocalContext.current

    Column(Modifier.fillMaxSize()) {
        Spacer(Modifier.height(20.dp))
        Button(onClick = {openWebBrowser(context, "http://www.google.com")}, Modifier.align(Alignment.CenterHorizontally)) { Text("Internet") }
        Spacer(Modifier.height(20.dp))
        Button(onClick = {openMaps(context, latitude = 67.7749, longitude = -102.4194)}, Modifier.align(Alignment.CenterHorizontally)) { Text("Show map location") }
        Spacer(Modifier.height(20.dp))
        Button(onClick = { createAlarm(context, "alarm", 9, 0) }, Modifier.align(Alignment.CenterHorizontally)) { Text("Set alarm") }
        Spacer(Modifier.height(20.dp))
        Button(onClick = { callPhoneNumber(context, "0401234567") }, Modifier.align(Alignment.CenterHorizontally)) { Text("Call number") }
    }
}

fun openWebBrowser(context: Context, url: String) {
    val webpage: Uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, webpage)
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}

fun openMaps(context: Context, latitude: Double, longitude: Double) {
    val locationUri = Uri.parse("geo:$latitude,$longitude")
    val intent = Intent(Intent.ACTION_VIEW, locationUri)

    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}

fun createAlarm(context: Context, message: String, hour: Int, minutes: Int) {
    val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
        putExtra(AlarmClock.EXTRA_MESSAGE, message)
        putExtra(AlarmClock.EXTRA_HOUR, hour)
        putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        putExtra(AlarmClock.EXTRA_SKIP_UI, false)
    }
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}

fun callPhoneNumber(context: Context, phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}
