package com.example.regres.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.regres.R
import com.example.regres.databinding.ActivityMainBinding
import com.example.regres.interfaces.UserLoginOnClick
import com.example.regres.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = UserViewModel(context=applicationContext)

        binding!!.user = viewModel
        binding!!.userOnClick = object : UserLoginOnClick {
            override fun userOnClickRegister() {
                viewModel.beRegister(binding!!.userName.text.toString(), binding!!.password.text.toString())
            }

        }

        viewModel.getResultUser().observe(this, Observer { isRegister->
            if(isRegister){
               startActivity(Intent(this,UserListActivity::class.java))
            }
        })

    }

}
