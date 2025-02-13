package com.ken.notesappwithhilt.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cheezycode.notesample.models.UserRequest
import com.cheezycode.notesample.models.UserResponse
import com.ken.notesappwithhilt.api.UserApi
import com.ken.notesappwithhilt.utils.Constants.TAG
import com.ken.notesappwithhilt.utils.NetworkResult
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userApi: UserApi,
) {

    private val _userResponseLiveData = MutableLiveData<NetworkResult<UserResponse>>()
    val userResponseLiveData: LiveData<NetworkResult<UserResponse>>
        get() = _userResponseLiveData

    suspend fun registerUser(userRequest: UserRequest) {
        _userResponseLiveData.postValue(NetworkResult.Loading())
        val response = userApi.signUp(userRequest)
        handleResponse(response)
        Log.d(TAG, "registerUser: ${response.body().toString()}")
    }

    suspend fun loginUser(userRequest: UserRequest) {
        _userResponseLiveData.postValue(NetworkResult.Loading())
        val response = userApi.signIn(userRequest)
        handleResponse(response)
        Log.d(TAG, "registerUser: ${response.body().toString()}")
    }

    private fun handleResponse(response: Response<UserResponse>) {
        try {
            if (response.isSuccessful && response.body() != null) {
                _userResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
            } else if (response.errorBody() != null) {
                _userResponseLiveData.postValue(NetworkResult.Error(response.body()!!.message))
            } else {
                _userResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
            }
        } catch (e: Exception) {
            _userResponseLiveData.postValue(NetworkResult.Error(e.message.toString()))
        }
    }
}