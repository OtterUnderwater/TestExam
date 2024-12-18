package com.example.testexam.models.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.testexam.models.database.Categories
import com.example.testexam.models.database.Memes

data class StateHome(
    var listMemes: List<Memes> = listOf(),
    var listCategories: MutableList<Categories> = mutableListOf(),
    var filterListMemes: List<Memes> = listOf(),
    var selectedCategory: Categories = Categories("0", "Все"),
    var search: String = "",
    var loading: MutableState<Boolean> = mutableStateOf(true)
)
