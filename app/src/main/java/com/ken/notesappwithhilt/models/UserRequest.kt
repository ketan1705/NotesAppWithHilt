package com.cheezycode.notesample.models

data class UserRequest(
    val email: String,
    val userPassword: String,
    val userName: String,
)