package com.musaan0129.assesment_mobpro1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.musaan0129.assesment_mobpro1.navigation.SetupNavGraph
import com.musaan0129.assesment_mobpro1.ui.theme.Assesment_Mobpro1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assesment_Mobpro1Theme {
                SetupNavGraph()
            }
        }
    }
}

