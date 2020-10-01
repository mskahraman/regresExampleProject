package com.example.regres.viewmodel

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.regres.connection.ApiManager
import com.example.regres.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(val context: Context) : ViewModel() {

    var progress: ObservableInt? = null
    private var result = MutableLiveData<Boolean>()


    init {
        progress = ObservableInt(View.GONE)

    }

    fun beRegister(email: String, pass: String) {
        progress?.set(View.VISIBLE)
        val apiCall = ApiManager().getApiInstance()
        val userList = apiCall.register(email, pass)
        userList.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG).show()
                progress?.set(View.GONE)
                result.value=false
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {

                if(response.isSuccessful){
                    result.value=true
//                    Toast.makeText(context, response.body()?.token.toString(), Toast.LENGTH_LONG).show()
                }else{
                    result.value=false
                    Toast.makeText(context, "Hatalı Giriş", Toast.LENGTH_LONG).show()

                }
                progress?.set(View.GONE)
            }


        })

    }

    fun getResultUser() : LiveData<Boolean>
    {
        return result
    }

}