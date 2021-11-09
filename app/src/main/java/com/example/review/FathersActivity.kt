package com.example.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.review.RoomDB.UsersEntity
import com.example.review.ViewModel.ViewModel
import kotlinx.android.synthetic.main.activity_fathers.*

class FathersActivity : AppCompatActivity() {
    private lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fathers)
        viewModel= ViewModelProvider(this)[ViewModel::class.java]
        getFathersData()
    }

    private fun getFathersData(){
        viewModel.getFathersData()!!.observe(this, {
            if(!it.isNullOrEmpty()){
                //here how to put data from API to your DB
               for (i in it){
                    viewModel.insertUser(UsersEntity(0,i.name,i.location))
                }
                rv_fathers.adapter=FathersRecyclerAdapter(it)
                rv_fathers.layoutManager=LinearLayoutManager(this)
            }else
                Toast.makeText(applicationContext, "No Data!", Toast.LENGTH_SHORT).show()
        })
    }
}