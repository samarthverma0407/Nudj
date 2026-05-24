package com.tpc.nudj

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.tpc.nudj.ui.DemoScreen
import com.tpc.nudj.ui.theme.NudjTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NudjTheme {
                DemoScreen()
            }
        }
    }
}