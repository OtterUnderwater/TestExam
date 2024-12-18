package com.example.testexam.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.testexam.view.bottomBar.BottomBar
import com.example.testexam.view.navigation.Navigation
import com.example.testexam.view.ui.theme.TestExamTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val controller = rememberNavController()
            val isVisibleBar = remember { mutableStateOf(false) }
            TestExamTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar =  { if (isVisibleBar.value) BottomBar(controller) }
                ) {
                   Navigation(controller, isVisibleBar)
                }
            }
        }
    }
}