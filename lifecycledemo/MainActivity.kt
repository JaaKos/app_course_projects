package com.example.lifecycledemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }

        Toast.makeText(this, "onCreate: Aktiviteetti luotu", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()

        Toast.makeText(this, "onStart: Aktiviteetti näkyvissä", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()

        Toast.makeText(this, "onResume: Aktiviteetti vuorovaikutteinen", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()

        Toast.makeText(this, "onPause: Aktiviteetti menossa taustalle", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()

        Toast.makeText(this, "onStop: Aktiviteetti ei enää näkyvissä", Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()

        Toast.makeText(this, "onRestart: Aktiviteetti käynnistyy uudelleen", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()

        Toast.makeText(this, "onDestroy: Aktiviteetti tuhotaan", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun App(counterClass: Counter = viewModel()) {
    val counter = counterClass.counter.observeAsState(0)

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = counter.value.toString(),
            fontSize = 48.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Row (Modifier.fillMaxWidth().padding(40.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = { counterClass.decrement() }
            ) {
                Text("Pienennä")
            }

            Button(
                onClick = { counterClass.increment() },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text("Kasvata")
            }
        }
    }
}