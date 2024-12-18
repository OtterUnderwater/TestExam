package com.example.testexam.models.database

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Memes(
    val id: String = "",
    val title: String = "",
    @SerialName("id_category")
    val idCategory: String = "",
    val photo: String = ""
)
