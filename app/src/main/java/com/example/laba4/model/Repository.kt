package com.example.laba4.model

import android.content.Context
import android.content.SharedPreferences
import com.example.laba4.R
import com.example.laba4.model.database.MyDatabase
import com.example.laba4.model.database.User
import com.example.laba4.model.database.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

class Repository (val context:Context) {
    private val userDao:UserDao
    private val sharedPref: SharedPreferences
    init{
        val db = MyDatabase.getDatabase(context)
        userDao = db.userDao
        sharedPref=context.getSharedPreferences(
            context.resources.getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        )
    }

    suspend fun signUp(email: String, password: String, name:String, points:Int, placeInRank:Int){
        withContext(Dispatchers.IO){userDao.insert(User(1,"none",name,email,password,placeInRank, points, true))}
    }

    suspend fun signIn(email: String, password: String) =
        withContext(Dispatchers.IO){userDao.signIn(email, password)}

    suspend fun checkIfUserExists(email:String, password:String):Boolean =
        withContext(Dispatchers.IO) {userDao.checkIfUserExists(email, password)}

    suspend fun isUserSignedInInDB():Boolean =
       withContext(Dispatchers.IO) { userDao.checkIfUserIsLoggedIn()}
    //            val defaultToken = context.resources.getString(R.string.defaultToken)
//            val storedToken = sharedPref.getString(
//                context.resources.getString(R.string.tokenKey),
//                defaultToken
//            )
//            //if (storedToken != null && storedToken!=defaultToken)
//                //Network.updateToken(storedToken)
//            return@withContext storedToken!=defaultToken
}