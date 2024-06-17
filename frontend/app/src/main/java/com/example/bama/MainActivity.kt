package com.example.bama

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.bama.ui.theme.BAMATheme

class MainActivity : ComponentActivity(

) {
    override fun onCreate(savedInstanceState: Bundle?) { // This is the main activity of the app
        super.onCreate(savedInstanceState)
        setContent {
            BAMATheme {
                BAMAApp()
            }
        }
    }
}