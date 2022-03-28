package com.example.myapplication.ui.users.model

data class User(
    val id: Long,

    val name: String,

    val email: String,

    val photo: Photo?
)
