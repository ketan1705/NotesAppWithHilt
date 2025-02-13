package com.ken.notesappwithhilt.api

import com.cheezycode.notesample.models.UserRequest
import com.cheezycode.notesample.models.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("user/signup")
    suspend fun signUp(@Body userRequest: UserRequest): Response<UserResponse>

    @POST("user/login")
    suspend fun signIn(@Body userRequest: UserRequest): Response<UserResponse>

}