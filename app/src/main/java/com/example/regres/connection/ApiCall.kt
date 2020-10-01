package com.example.regres.connection

import com.example.regres.model.User
import com.example.regres.model.UserListResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiCall {

    @FormUrlEncoded
    @POST("register")
    fun register(@Field("email") email : String,@Field("password") password:String ):Call<User>

    @GET("users")
    fun getList():Call<UserListResponse>


}