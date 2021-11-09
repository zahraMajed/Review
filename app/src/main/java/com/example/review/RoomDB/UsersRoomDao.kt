package com.example.review.RoomDB

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface UsersRoomDao {

    @Query("select * from Users")
    fun getAllUsersInfo(): LiveData<List<UsersEntity>>
    @Insert
    fun insertUser(user: UsersEntity)
    @Update
    fun updateUserInfo(user: UsersEntity)
    @Query("DELETE FROM Users WHERE UserName = :userName")
    fun deleteUserByName(userName: String)


}