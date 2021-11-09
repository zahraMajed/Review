package com.example.review.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.review.Retrofit.APIClint
import com.example.review.Retrofit.APIInterface
import com.example.review.Retrofit.FathersAPIModel
import com.example.review.RoomDB.UsersEntity
import com.example.review.RoomDB.UsersRoomDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository (private val usersRoomDatabase: UsersRoomDatabase) {

    private val apiInterface: APIInterface = APIClint().getClient()!!.create(APIInterface::class.java)
    val fathersListData=MutableLiveData<List<FathersAPIModel.FathersAPIModelItem>>()
    val getFathersDataErrorMsg=MutableLiveData<String>()

    fun getFathersData(): MutableLiveData<List<FathersAPIModel.FathersAPIModelItem>> {
         apiInterface.getFathersData().enqueue(object : Callback<List<FathersAPIModel.FathersAPIModelItem>?> {
             override fun onResponse(call: Call<List<FathersAPIModel.FathersAPIModelItem>?>, response: Response<List<FathersAPIModel.FathersAPIModelItem>?>) {
                 fathersListData.value=response.body() }

             override fun onFailure(call: Call<List<FathersAPIModel.FathersAPIModelItem>?>, t: Throwable) {
                 getFathersDataErrorMsg.value="Fail to get Fathers data!" }
         })
        return fathersListData
    }

    fun getAllUsersInfo() : LiveData<List<UsersEntity>>{
        return usersRoomDatabase.getUsersRoomDao().getAllUsersInfo()
    }
    suspend fun insertUser(user: UsersEntity){
        usersRoomDatabase.getUsersRoomDao().insertUser(user)
    }
    suspend fun deleteUserByName(userName: String) {
        usersRoomDatabase.getUsersRoomDao().deleteUserByName(userName)
    }
    suspend fun updateUserInfo(user: UsersEntity) {
        usersRoomDatabase.getUsersRoomDao().updateUserInfo(user)
    }

}