package com.musaan0129.assesment_mobpro1.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.musaan0129.assesment_mobpro1.R
import com.musaan0129.assesment_mobpro1.navigation.Screen
import com.musaan0129.assesment_mobpro1.ui.theme.Assesment_Mobpro1Theme
import kotlin.math.pow
import kotlin.math.sqrt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navContoller: NavHostController){
    Scaffold (
        topBar = {
            TopAppBar(

                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick = {
                        navContoller.navigate(Screen.About.route)
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.tentang_aplikasi),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    )
    { innerPadding ->
        ScreenContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier){
    var selectedShape by remember { mutableStateOf("Kubus") }
    val shapeOptions = listOf("Kubus","Balok","Prisma","Bola")

    var sisi by remember { mutableStateOf("") }
    var panjang by remember { mutableStateOf("") }
    var lebar by remember { mutableStateOf("") }
    var tinggi by remember { mutableStateOf("") }
    var alas by remember { mutableStateOf("") }
    var tinggiAlas by remember { mutableStateOf("") }
    var jariJari by remember { mutableStateOf("") }

    var volume by remember { mutableStateOf("") }
    var luasPermukaan by remember { mutableStateOf("") }


    Column (
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = stringResource(id = R.string.intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )

        Text("Pilih bangun ruang")
        BangunDropdown(selectedShape, shapeOptions, onSelected = {selectedShape = it})

        when (selectedShape) {
            "Kubus" -> {
                InputField(label = "Sisi", value = sisi, onValueChange = {sisi = it})
            }
            "Balok" -> {
                InputField(label = "Panjang", value = panjang, onValueChange = {panjang = it})
                InputField(label = "Lebar", value = lebar, onValueChange = {lebar = it})
                InputField(label = "Tinggi", value = tinggi, onValueChange = {tinggi = it})
            }
            "Prisma" -> {
                InputField(label = "Alas Segitiga", value = alas, onValueChange = {alas = it})
                InputField(label = "Tinggi Segitiga", value = tinggiAlas, onValueChange = {tinggiAlas = it})
                InputField(label = "Tinggi", value = tinggi, onValueChange = {tinggi = it})
            }
            "Bola" -> {
                InputField(label = "Jari-jari", value = jariJari, onValueChange = {jariJari = it})
            }
        }



        androidx.compose.material3.Button(
            onClick = {
                when (selectedShape) {
                    "Kubus" -> {
                        val s = sisi.toDoubleOrNull() ?: 0.0
                        volume = (s * s * s).toString()
                        luasPermukaan = (6 * s * s).toString()
                    }
                    "Balok" -> {
                        val p = panjang.toDoubleOrNull() ?: 0.0
                        val l = lebar.toDoubleOrNull() ?: 0.0
                        val t = tinggi.toDoubleOrNull() ?: 0.0
                        volume = (p * l * t).toString()
                        luasPermukaan = (2 * (p * l + p * t + l * t)).toString()
                    }
                    "Prisma" -> {
                        val a = alas.toDoubleOrNull() ?: 0.0
                        val ta = tinggiAlas.toDoubleOrNull() ?: 0.0
                        val tp = tinggi.toDoubleOrNull() ?: 0.0
                        val kartesius = sqrt((a / 2).pow(2.0) + ta.pow(2.0))
                        val kelilingAlas = a+(2*kartesius)
                        val luasAlas = 0.5 * a * ta
                        volume = (luasAlas * tp).toString()
                        luasPermukaan = ((2*luasAlas)+(kelilingAlas*tp)).toString()


                    }
                    "Bola" -> {
                        val r = jariJari.toDoubleOrNull() ?: 0.0
                        volume = ((4 / 3.0) * Math.PI * r * r * r).toString()
                        luasPermukaan = (4 * Math.PI * r * r).toString()
                    }
                }
            }
        ) {
            Text("Hitung")
        }

        if (volume.isNotEmpty()) {
            Text("Volume: $volume")
            Text("Luas Permukaan: $luasPermukaan")

        }
    }
}

@Composable
fun BangunDropdown(
    selectedOption: String,
    options: List<String>,
    onSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text("Bangun Ruang") },
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}


@Composable
fun InputField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        trailingIcon = { Text("cm") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        modifier = Modifier.fillMaxWidth()
    )
}




@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Assesment_Mobpro1Theme {
        MainScreen(rememberNavController())
    }
}