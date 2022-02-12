package com.example.mvvmsample.viewmodel

import android.view.animation.Transformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mvvmsample.model.IUserRepository
import com.example.mvvmsample.model.User
import com.example.mvvmsample.model.UserRepository

class UserViewModel: ViewModel() {
    private val repository:IUserRepository = UserRepository.getInstance()
    private val userId = MutableLiveData<String>()

    val user:LiveData<User> = Transformations.switchMap(userId){ userId ->
        repository.getUser(userId)
    }

    fun onItemSelected(item:String){
        userId.value = item
    }
}