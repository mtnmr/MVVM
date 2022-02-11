package com.example.mvvmsample.model

data class User(
    val id:String,
    val name:String,
    val iconId:Int,
    val isFavoirte:Boolean
)

class UserBuilder{
    private val mId:String = ""
    private val mName:String = ""
    private val mIconId:Int = 0
    private val mIsFavorite:Boolean = false

    fun setId(id:String):UserBuilder{
        mId = id
        return this
    }

    fun setName(name:String):UserBuilder{
        mName = name
        return this
    }

    fun setIconId(iconId: Int):UserBuilder{
        mIconId = iconId
        return this
    }

    fun setIsFavorite(isFavoirte: Boolean):UserBuilder{
        mIsFavorite = isFavoirte
        return this
    }

    fun build():User{
        return User(mId, mName, mIconId, mIsFavorite)
    }
}

