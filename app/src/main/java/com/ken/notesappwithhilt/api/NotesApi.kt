package com.ken.notesappwithhilt.api

import com.cheezycode.notesample.models.NoteRequest
import com.cheezycode.notesample.models.NoteResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface NotesApi {
    @GET("note/get-all")
    suspend fun getNotes(): Response<NoteResponse>

    @POST("note/create_note")
    suspend fun createNotes(@Body noteRequest: NoteRequest): Response<NoteResponse>

    @PUT("note/update_note/id/{noteId}")
    suspend fun updateNotes(
        @Path("noteId") noteId: String,
        @Body noteRequest: NoteRequest,
    ): Response<NoteResponse>

    @DELETE("note/delete_note/id/{noteId}")
    suspend fun deleteNotes(@Path("noteId") noteId: String): Response<NoteResponse>
}