package com.example.testexam.models.database

import kotlinx.serialization.Serializable

@Serializable
data class Users (
    val id: String = "",
    val login: String = ""
)