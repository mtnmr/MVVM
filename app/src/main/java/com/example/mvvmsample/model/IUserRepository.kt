package com.example.mvvmsample.model

interface IUserRepository {

    fun getUser(userId : String):LiveData<User>
}