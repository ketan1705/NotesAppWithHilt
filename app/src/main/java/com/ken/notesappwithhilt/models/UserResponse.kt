package com.cheezycode.notesample.models

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("token")
    val token: String,
    @SerializedName("data")
    val data: User,
)