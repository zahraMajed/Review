package com.example.review

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.review.RoomDB.UsersEntity
import com.example.review.ViewModel.ViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var etUserName:EditText
    private lateinit var erUserLocation:EditText
    private lateinit var btnSaveName:Button
    private lateinit var btnViewAllNames:Button
    private lateinit var btnUpdateName:Button
    private lateinit var btnDeleteNames:Button
    private lateinit var btnGoFatherActivity:Button
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUserName=findViewById(R.id.etUserName)
        erUserLocation=findViewById(R.id.etUserLocation)
        btnSaveName=findViewById(R.id.btnSaveName)
        btnViewAllNames=findViewById(R.id.btnViewNames)
        btnUpdateName=findViewById(R.id.btnUpdateName)
        btnDeleteNames=findViewById(R.id.btnDeleteName)
        btnGoFatherActivity=findViewById(R.id.btnGoFatherActivity)
        viewModel= ViewModelProvider(this)[ViewModel::class.java]

        btnSaveName.setOnClickListener {
            saveUser()
        }

        btnUpdateName.setOnClickListener{
            updateData()
        }

        btnDeleteNames.setOnClickListener{
            deleteUser()
        }

        btnViewAllNames.setOnClickListener {
            viewAllUsersName()
        }

        btnGoFatherActivity.setOnClickListener{
            intent=Intent(this,FathersActivity::class.java)
            startActivity(intent)
        }

        viewAllUsersName()
    }

    private fun deleteUser() {
        if (etUserName.text.isNotEmpty()){
            viewModel.deleteUserByName(etUserName.text.toString())
            clearEditText()
        }else
            toastMissingEntries()
    }

    private fun updateData() {
        if (etUserName.text.isNotEmpty() && erUserLocation.text.isNotEmpty()){
            viewModel.updateUserInfo(UsersEntity(0,etUserName.text.toString(),erUserLocation.text.toString()))
            clearEditText()
        }else
            toastMissingEntries()
    }

    private fun viewAllUsersName() {
        viewModel.getAllUsersInfo().observe(this, {
            if (!it.isNullOrEmpty()){
                rv_main.adapter=UsersRecyclerAdapter(it)
                rv_main.layoutManager=LinearLayoutManager(this)
            }else
                Toast.makeText(applicationContext, "No data!", Toast.LENGTH_SHORT).show()
        })
    }

    private fun saveUser(){
        if (etUserName.text.isNotEmpty() && erUserLocation.text.isNotEmpty()){
            viewModel.insertUser(UsersEntity(0,etUserName.text.toString(),erUserLocation.text.toString()))
            clearEditText()
        }else
            toastMissingEntries()
    }

    private fun clearEditText() {
        etUserName.text.clear()
        erUserLocation.text.clear()
    }

    private fun toastMissingEntries(){
        Toast.makeText(this, "Please fill missing entries", Toast.LENGTH_SHORT).show()
    }
}