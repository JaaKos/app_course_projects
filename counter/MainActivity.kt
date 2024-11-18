package com.example.viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
    }
}

@Preview(showBackground = true)
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
