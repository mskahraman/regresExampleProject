package com.example.regres.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.regres.model.Data
import android.widget.TextView
import android.view.View
import android.widget.ImageView
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.regres.R
import com.squareup.picasso.Picasso


class CustomAdapter(val context: Context, var arrayList:ArrayList<Data>,var onSelectedItem : OnSelectedItem): RecyclerView.Adapter<CustomAdapter.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val listItem = layoutInflater.inflate(R.layout.user_list_inner_layout, parent, false)
        return ViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.usersName.text=arrayList[position].first_name
        holder.usersEmail.text=arrayList[position].email
        Picasso.get().load(arrayList[position].avatar).placeholder(R.drawable.ic_launcher_background).into(holder.imageViewUser)

        holder.root.setOnClickListener{
            onSelectedItem.getSelectedItem(position)
        }

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageViewUser: ImageView
        var usersName: TextView
        var usersEmail: TextView
        var root: LinearLayout

        init {
            this.imageViewUser = itemView.findViewById(R.id.imageV)
            this.usersName = itemView.findViewById(R.id.usersName)
            this.usersEmail = itemView.findViewById(R.id.usersEmail)
            this.root = itemView.findViewById(R.id.rootLayout)
        }
    }


    interface OnSelectedItem{
        fun getSelectedItem(item:Int)
    }
}