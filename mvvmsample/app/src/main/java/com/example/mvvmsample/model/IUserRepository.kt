package com.example.mvvmsample.model

import androidx.lifecycle.LiveData

interface IUserRepository {

    fun getUser(userId : String): LiveData<User>
}
