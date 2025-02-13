package com.cheezycode.notesample.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class User(

    @SerializedName("email")
    val email: String,
    @SerializedName("userPassword")
    val userpassword: String,
    @SerializedName("userName")
    val username: String,
) : Serializable