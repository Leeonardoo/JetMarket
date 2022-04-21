package io.github.leeonardoo.jetmarket.ui.user.list.model

data class User(
    val id: Long,

    val name: String,

    val email: String,

    val photo: Photo?
)
