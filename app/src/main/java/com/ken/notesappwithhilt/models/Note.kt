package com.ken.notesappwithhilt.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Note(
    @SerializedName("notesId")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("userId")
    val userId: String,
) : Serializable
