package com.cheezycode.notesample.models

import com.google.gson.annotations.SerializedName
import com.ken.notesappwithhilt.models.Note
import java.io.Serializable

data class NoteResponse(
    @SerializedName("data")
    val data: List<Note>,
    @SerializedName("success")
    val success: String,
    @SerializedName("message")
    val message: String,
) : Serializable