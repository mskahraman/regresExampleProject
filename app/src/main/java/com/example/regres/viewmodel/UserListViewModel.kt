package com.example.regres.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.regres.connection.ApiManager
import com.example.regres.model.Data
import com.example.regres.model.UserListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListViewModel : ViewModel() {

    init {
        getUserListResponse()
    }

    private var userListData = MutableLiveData<ArrayList<Data>>()

    private fun getUserListResponse() {
        val apiCall = ApiManager().getApiInstance()
        val userList = apiCall.getList()
        userList.enqueue(object : Callback<UserListResponse> {
            override fun onFailure(call: Call<UserListResponse>, t: Throwable) {
                Log.d("UserListResponse", t.localizedMessage.toString())
            }

            override fun onResponse(call: Call<UserListResponse>, response: Response<UserListResponse>) {

                if (response.isSuccessful) {
                    userListData.value = response.body()?.data
                }
            }
        })
    }

    fun getUserList(): LiveData<ArrayList<Data>> {
        return userListData
    }
}