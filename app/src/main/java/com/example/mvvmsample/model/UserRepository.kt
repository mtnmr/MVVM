package com.example.mvvmsample.model

import androidx.lifecycle.LiveData

class UserRepository : IUserRepository {
    private val dataSource = UserDataSource()

    override fun getUser(userId:String):LiveData<User>(){
        return dataSource.getUser(userId)
    }

    companion object{
        @Volatile
        private val instance : UserRepository? = null

        fun getInstance(): UserRepository{
            return instance ?: synchronized(this){
                instance ?: UserRepository().also { instance = it }
            }
        }
    }
}