package com.musaan0129.assesment_mobpro1.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.musaan0129.assesment_mobpro1.R
import com.musaan0129.assesment_mobpro1.navigation.Screen
import com.musaan0129.assesment_mobpro1.ui.theme.Assesment_Mobpro1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navContoller:NavHostController){
    Scaffold (
        topBar = {
            TopAppBar(

                navigationIcon = {
                    IconButton(onClick = {
                        navContoller.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = stringResource(R.string.tentang_aplikasi),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },

                title = {
                    Text(text = stringResource(id = R.string.tentang_aplikasi))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    )
    { innerPadding ->
        Text(
            text = stringResource(R.string.copyright),
            modifier = Modifier.padding(innerPadding).padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun AboutScreenPreview(){
    Assesment_Mobpro1Theme {
        AboutScreen(rememberNavController())
    }
}