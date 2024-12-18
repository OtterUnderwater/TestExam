package com.example.testexam.models.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.testexam.models.database.Categories
import com.example.testexam.models.database.Memes

data class StateAuth(
    var login: String = "",
    var password: String = ""
)
