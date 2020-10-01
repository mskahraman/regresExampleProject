package com.example.regres.connection

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {

    private val BASE_URL: String = "https://reqres.in/api/"
    private fun getRetrofitInstance(): Retrofit {

        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient()).build()

    }

    fun getApiInstance(): ApiCall {

        return getRetrofitInstance().create(ApiCall::class.java)
    }

}