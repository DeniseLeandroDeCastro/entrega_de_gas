package com.example.entregadegas.data

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val imagePather: String = ""
) {
    constructor(): this("", "", "", "")
}
