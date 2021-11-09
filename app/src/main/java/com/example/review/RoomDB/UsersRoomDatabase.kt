package com.example.review.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities= [UsersEntity::class], version = 1, exportSchema = false)
abstract class UsersRoomDatabase:RoomDatabase() {
    companion object{
        var instance:UsersRoomDatabase?=null
        fun getInstance(context: Context):UsersRoomDatabase {
            if(instance!=null){
                return instance as UsersRoomDatabase
            }
            instance= Room.databaseBuilder(context,UsersRoomDatabase::class.java, "data").run {allowMainThreadQueries()}
                .fallbackToDestructiveMigration()
                .build()
            return instance as UsersRoomDatabase
        }
    }
    abstract fun getUsersRoomDao():UsersRoomDao
}