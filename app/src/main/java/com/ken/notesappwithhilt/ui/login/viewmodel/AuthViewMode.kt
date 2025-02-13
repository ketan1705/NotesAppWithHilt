package com.ken.notesappwithhilt.ui.login.viewmodel

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheezycode.notesample.models.UserRequest
import com.cheezycode.notesample.models.UserResponse
import com.ken.notesappwithhilt.repository.UserRepository
import com.ken.notesappwithhilt.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewMode @Inject constructor(val repository: UserRepository) : ViewModel() {

    val userResponseLiveData: LiveData<NetworkResult<UserResponse>>
        get() = repository.userResponseLiveData

    fun registerUser(userRequest: UserRequest) {
        viewModelScope.launch {
            repository.registerUser(userRequest)
        }
    }

    fun loginUser(userRequest: UserRequest) {
        viewModelScope.launch {
            repository.loginUser(userRequest)
        }
    }

    fun registerValidateCredentials(
        email: String,
        username: String,
        password: String,
    ): Pair<Boolean, String> {

        var result = Pair(true, "")
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(username)
            || TextUtils.isEmpty(password)
        ) {
            result = Pair(false, "Please fill all the fields")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            result = Pair(false, "Please enter a valid email")
        } else if (password.length < 6) {
            result = Pair(false, "Password should be at least 6 characters")
        } else if (username.length < 3) {
            result = Pair(false, "Username should be at least 3 characters")
        }
        return result

    }

    fun loginValidateCredentials(
        username: String,
        password: String,
    ): Pair<Boolean, String> {

        var result = Pair(true, "")
        if (TextUtils.isEmpty(username)
            || TextUtils.isEmpty(password)
        ) {
            result = Pair(false, "Please fill all the fields")
        } else if (password.length < 6) {
            result = Pair(false, "Password should be at least 6 characters")
        } else if (username.length < 3) {
            result = Pair(false, "Username should be at least 3 characters")
        }
        return result

    }
}