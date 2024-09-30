package com.example.hintalaskuri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hintalaskuri.ui.theme.HintalaskuriTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HintalaskuriTheme {
                App()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun App() {
    var gasPrice by remember { mutableStateOf("") }
    var carConsumption by remember { mutableStateOf("") }
    var distance by remember { mutableStateOf("") }
    var totalCost by remember { mutableStateOf("0.00") }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(stringResource(R.string.App_title), fontSize = 32.sp)
                }
            )
        }
    )
    {
        innerPadding ->
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding))
        {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
            {
                OutlinedTextField (
                    value = gasPrice,
                    onValueChange = {gasPrice = it},
                    label = { Text(stringResource(R.string.Gas_price)) },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(Modifier.height(4.dp))
                OutlinedTextField (
                    value = carConsumption,
                    onValueChange = {carConsumption = it},
                    label = { Text(stringResource(R.string.car_consumption_100km)) },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(Modifier.height(4.dp))
                OutlinedTextField (
                    value = distance,
                    onValueChange = {distance = it},
                    label = { Text(stringResource(R.string.distance)) },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(Modifier.height(8.dp))
                Button( onClick = {
                    totalCost = "%.2f".format(calculatePrice(gasPrice, carConsumption, distance))
                },
                    Modifier
                        .width(300.dp)
                        .align(Alignment.CenterHorizontally)) {
                    Text(stringResource(R.string.button_text))
                }
                Spacer(Modifier.height(24.dp))
                Text(stringResource(R.string.total_cost, totalCost), fontSize = 18.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
        }
    }
}

fun calculatePrice(gasPrice: String, carConsumption: String, distance: String ): Double {
    val gasPriceConverted = gasPrice.toDouble()
    val carConsumptionConverted = carConsumption.toDouble()
    val distanceConverted = distance.toDouble()
    return gasPriceConverted * (carConsumptionConverted / 100) * distanceConverted
}
