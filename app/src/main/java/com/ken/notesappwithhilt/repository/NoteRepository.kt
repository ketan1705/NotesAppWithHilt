package com.ken.notesappwithhilt.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cheezycode.notesample.models.NoteRequest
import com.cheezycode.notesample.models.NoteResponse
import com.ken.notesappwithhilt.api.NotesApi
import com.ken.notesappwithhilt.utils.NetworkResult
import retrofit2.Response
import javax.inject.Inject

class NoteRepository @Inject constructor(private val notesApi: NotesApi) {
    private val _notesLiveData = MutableLiveData<NetworkResult<NoteResponse>>()

    val notesLiveData: LiveData<NetworkResult<NoteResponse>>
        get() = _notesLiveData

    suspend fun getNotes() {
        _notesLiveData.postValue(NetworkResult.Loading())
        val response = notesApi.getNotes()
        handleResponse(response)
    }

    suspend fun createNote(noteRequest: NoteRequest) {
        _notesLiveData.postValue(NetworkResult.Loading())
        val response = notesApi.createNotes(noteRequest)
        handleResponse(response)
    }

    suspend fun updateNote(id: String, noteRequest: NoteRequest) {
        _notesLiveData.postValue(NetworkResult.Loading())
        val response = notesApi.updateNotes(id, noteRequest)
        handleResponse(response)
    }

    suspend fun deleteNote(id: String) {
        _notesLiveData.postValue(NetworkResult.Loading())
        val response = notesApi.deleteNotes(id)
        handleResponse(response)
    }

    private fun handleResponse(response: Response<NoteResponse>) {
        try {
            if (response.isSuccessful && response.body() != null) {
                _notesLiveData.postValue(NetworkResult.Success(response.body()!!))
            } else if (response.errorBody() != null) {
                _notesLiveData.postValue(NetworkResult.Error(response.body()!!.message))
            } else {
                _notesLiveData.postValue(NetworkResult.Error("Something went wrong"))
            }
        } catch (e: Exception) {
            _notesLiveData.postValue(NetworkResult.Error(e.message.toString()))
        }
    }
}