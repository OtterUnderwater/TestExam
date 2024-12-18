package com.example.testexam.models.database

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavoriteMemes(
    val id: String = "",
    @SerialName("id_user")
    val idUser: String = "",
    @SerialName("id_meme")
    val idMeme: String = "",
)
