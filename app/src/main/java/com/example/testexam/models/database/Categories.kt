package com.example.testexam.models.database

import kotlinx.serialization.Serializable

@Serializable
data class Categories(
    val id: String = "",
    val title: String = ""
)
