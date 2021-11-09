package com.example.review.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.review.Repository.Repository
import com.example.review.Retrofit.APIClint
import com.example.review.Retrofit.APIInterface
import com.example.review.Retrofit.FathersAPIModel
import com.example.review.RoomDB.UsersEntity
import com.example.review.RoomDB.UsersRoomDatabase
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ViewModel(app:Application):AndroidViewModel(app) {

    private val repository:Repository = Repository(UsersRoomDatabase.getInstance(app))
    private var fathersListData: MutableLiveData<List<FathersAPIModel.FathersAPIModelItem>>? = null


    fun getFathersData(): MutableLiveData<List<FathersAPIModel.FathersAPIModelItem>>? {
        fathersListData= repository.getFathersData()
        return fathersListData
    }

    fun getAllUsersInfo(): LiveData<List<UsersEntity>> = repository.getAllUsersInfo()

    fun insertUser(user: UsersEntity){
        viewModelScope.launch { repository.insertUser(user)} }
    fun updateUserInfo(user: UsersEntity){
        viewModelScope.launch {repository.updateUserInfo(user)} }
    fun deleteUserByName(userName: String){
        viewModelScope.launch {repository.deleteUserByName(userName)} }

}