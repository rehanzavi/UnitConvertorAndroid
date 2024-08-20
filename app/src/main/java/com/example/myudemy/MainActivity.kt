package com.example.myudemy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myudemy.ui.theme.MyUdemyTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyUdemyTheme {
                UnitConverter()
            }
        }
    }
}

@Composable
fun UnitConverter() {
    var inputValue by remember {
        mutableStateOf("")
    }
    var outputValue by remember {
        mutableStateOf("")
    }
    var inputUnit by remember {
        mutableStateOf("Centimeters")
    }
    var outputUnit by remember {
        mutableStateOf("Meters")
    }
    var iExpanded by remember {
        mutableStateOf(false)
    }
    var oExpanded by remember {
        mutableStateOf(false)
    }
    var iconversionFactor = remember {
        mutableStateOf(0.01)
    }
    var oconversionFactor = remember {
        mutableStateOf(0.01)
    }

    fun convert() {
        val inputVal = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputVal * iconversionFactor.value / oconversionFactor.value * 100.0).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Converter")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = inputValue,
            onValueChange = {
            inputValue = it
                convert()
        },
            label = { Text(text = "Enter value")})

        Spacer(modifier = Modifier.height(16.dp))

        //Input Box
        Row {
            Box {
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "centimeter")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "centimeter"
                            iconversionFactor.value = 0.01
                            convert()
                        })
                    DropdownMenuItem(text = { Text(text = "meter")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "meter"
                            iconversionFactor.value = 1.0
                            convert()
                        })
                    DropdownMenuItem(text = { Text(text = "feet")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "feet"
                            iconversionFactor.value = 0.3048
                            convert()}
                    )
                    DropdownMenuItem(text = { Text(text = "milimeters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "milimeter"
                            iconversionFactor.value = 0.001
                            convert()
                        })
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            //Output Box
            Box {
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "centimeter") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "centimeter"
                            oconversionFactor.value = 0.01
                            convert()
                        })
                    DropdownMenuItem(text = { Text(text = "meter") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "meter"
                            oconversionFactor.value = 1.0
                            convert()
                        })
                    DropdownMenuItem(text = { Text(text = "feet") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "feet"
                            oconversionFactor.value = 0.3048
                            convert()
                        })
                    DropdownMenuItem(text = { Text(text = "milimeters") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "milimeter"
                            oconversionFactor.value = 0.001
                            convert()
                        })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyUdemyTheme {
        UnitConverter()
    }
}