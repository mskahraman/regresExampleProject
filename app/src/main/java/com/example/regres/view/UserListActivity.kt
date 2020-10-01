package com.example.regres.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.regres.R
import com.example.regres.adapter.CustomAdapter
import com.example.regres.viewmodel.UserListViewModel

class UserListActivity : AppCompatActivity(),CustomAdapter.OnSelectedItem {

    private var userListViewModel: UserListViewModel? = null
    private var list : RecyclerView?=null
    private var myAdapter:CustomAdapter?=null

    private var onSelectedItem: CustomAdapter.OnSelectedItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        list=findViewById(R.id.userList)

        userListViewModel = ViewModelProviders.of(this).get(UserListViewModel::class.java)

        userListViewModel?.getUserList()?.observe(this, Observer { userList ->
            myAdapter= CustomAdapter(this,userList,this)
            list?.layoutManager=LinearLayoutManager(this)
            list?.adapter=myAdapter

        })
//
//        onSelectedItem = object :  CustomAdapter.OnSelectedItem {
//            override fun getSelectedItem(item: Int) {
//
//
//            }
//
//        }
    }

    override fun getSelectedItem(item: Int) {
        Toast.makeText(applicationContext,item.toString(), Toast.LENGTH_SHORT).show()
    }
}
