package com.musaan0129.assesment_mobpro1.ui.screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
    val bentuk = stringResource(R.string.bentuk)
    var selectedShape by rememberSaveable { mutableStateOf(bentuk) }
    val shapeOptions = listOf(stringResource(R.string.kubus),stringResource(R.string.balok),stringResource(R.string.prisma),stringResource(R.string.bola))

    val kubus = stringResource(R.string.kubus)
    val balok = stringResource(R.string.balok)
    val prisma = stringResource(R.string.prisma)
    val bola = stringResource(R.string.bola)

    val context = LocalContext.current

    var sisi by rememberSaveable { mutableStateOf("") }
    var panjang by rememberSaveable { mutableStateOf("") }
    var lebar by rememberSaveable { mutableStateOf("") }
    var tinggi by rememberSaveable { mutableStateOf("") }
    var alas by rememberSaveable { mutableStateOf("") }
    var tinggiAlas by rememberSaveable { mutableStateOf("") }
    var jariJari by rememberSaveable { mutableStateOf("") }

    var volume by rememberSaveable { mutableStateOf("") }
    var luasPermukaan by rememberSaveable { mutableStateOf("") }

    val scrollState = rememberScrollState()



    Column (
        modifier = modifier.fillMaxSize().padding(16.dp).verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(12.dp)

    ) {

        Text(
            text = stringResource(id = R.string.intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )

        Text(stringResource(R.string.pilih))
        BangunDropdown(selectedShape, shapeOptions, onSelected = {selectedShape = it})

        when (selectedShape) {
            stringResource(R.string.kubus) -> {
                Image(
                    painter = painterResource(R.drawable.kubus),
                    contentDescription = stringResource(R.string.kubus),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).size(150.dp)
                )
                InputField(label = stringResource(R.string.sisi), value = sisi, onValueChange = {sisi = it})
            }
            stringResource(R.string.balok) -> {
                Image(
                    painter = painterResource(R.drawable.balok),
                    contentDescription = stringResource(R.string.balok),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).size(150.dp)
                )
                InputField(label = stringResource(R.string.panjang), value = panjang, onValueChange = {panjang = it})
                InputField(label = stringResource(R.string.lebar), value = lebar, onValueChange = {lebar = it})
                InputField(label = stringResource(R.string.tinggi), value = tinggi, onValueChange = {tinggi = it})
            }
            stringResource(R.string.prisma) -> {
                Image(
                    painter = painterResource(R.drawable.prisma),
                    contentDescription = stringResource(R.string.prisma),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).size(150.dp)
                )
                InputField(label = stringResource(R.string.alas), value = alas, onValueChange = {alas = it})
                InputField(label = stringResource(R.string.tinggiAlas), value = tinggiAlas, onValueChange = {tinggiAlas = it})
                InputField(label = stringResource(R.string.tinggi), value = tinggi, onValueChange = {tinggi = it})
            }
            stringResource(R.string.bola) -> {
                Image(
                    painter = painterResource(R.drawable.bola),
                    contentDescription = stringResource(R.string.bola),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).size(150.dp)
                )
                InputField(label = stringResource(R.string.jari_jari), value = jariJari, onValueChange = {jariJari = it})
            }
        }



        Button(
            onClick = {
                when (selectedShape) {
                    kubus -> {
                        val s = sisi.toDoubleOrNull() ?: 0.0
                        volume = (s * s * s).toString()
                        luasPermukaan = (6 * s * s).toString()
                    }
                    balok -> {
                        val p = panjang.toDoubleOrNull() ?: 0.0
                        val l = lebar.toDoubleOrNull() ?: 0.0
                        val t = tinggi.toDoubleOrNull() ?: 0.0
                        volume = (p * l * t).toString()
                        luasPermukaan = (2 * (p * l + p * t + l * t)).toString()
                    }
                    prisma -> {
                        val a = alas.toDoubleOrNull() ?: 0.0
                        val ta = tinggiAlas.toDoubleOrNull() ?: 0.0
                        val tp = tinggi.toDoubleOrNull() ?: 0.0
                        val kartesius = sqrt((a / 2).pow(2.0) + ta.pow(2.0))
                        val kelilingAlas = a+(2*kartesius)
                        val luasAlas = 0.5 * a * ta
                        volume = (luasAlas * tp).toString()
                        luasPermukaan = ((2*luasAlas)+(kelilingAlas*tp)).toString()


                    }
                    bola -> {
                        val r = jariJari.toDoubleOrNull() ?: 0.0
                        volume = ((4 / 3.0) * Math.PI * r * r * r).toString()
                        luasPermukaan = (4 * Math.PI * r * r).toString()
                    }
                }
            }
        ) {
            Text(stringResource(R.string.hitung))
        }

        if (volume.isNotEmpty()) {
            Text("Volume: $volume")
            Text("Luas Permukaan: $luasPermukaan")

        }

        Button(
            onClick = {
                shareData(
                    context = context,
                    message = context.getString(R.string.bagikan_template, volume, luasPermukaan
                    )
                )
            },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical=16.dp)
        ) {
            Text(
                text = stringResource(R.string.bagikan)
            )
        }
    }
}

@Composable
fun BangunDropdown(
    selectedOption: String,
    options: List<String>,
    onSelected: (String) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text(stringResource(R.string.pilih)) },
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

private fun shareData(context: Context, message: String){
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}




@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Assesment_Mobpro1Theme {
        MainScreen(rememberNavController())
    }
}