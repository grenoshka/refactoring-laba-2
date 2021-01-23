package com.example.laba4.model.database

import androidx.room.*

@Dao
interface UserDao {

    // Добавление Object в бд
    @Insert
    fun insert(myObject: User)

    // Удаление Object из бд
    @Delete
    fun delete(myObject: User)

    // Обновление Object в бд
    @Update
    fun update(myObject: User)

    // Получение всех Object из бд
    @Query("SELECT * FROM myUsers ORDER BY points DESC")
    fun getAllUsersOrderedByPoints(): List<User>

    @Query("SELECT EXISTS(SELECT * FROM myUsers WHERE isLoggedIn=1)")
    fun checkIfUserIsLoggedIn():Boolean

    @Query("SELECT EXISTS(SELECT * FROM myUsers WHERE email =:email and password =:password)")
    fun checkIfUserExists(email:String, password:String):Boolean

    @Query("UPDATE myUsers set isLoggedIn =1 WHERE email =:email and password =:password")
    fun signIn(email:String, password:String)

    @Query("SELECT * FROM myUsers WHERE isLoggedIn=1 LIMIT 1")
    fun getSignedInUser():User

    @Query("SELECT COUNT(*) FROM myUsers WHERE points > (SELECT points from myUsers where email =:email and password =:password) + 1")
    fun getPlaceOnLeaderboard(email:String, password: String):Int

    @Query("UPDATE myUsers set points =:points WHERE id=:id")
    fun updateUserPoints(id:Int, points:Int)
    // Получение всех Object из бд с условием
    //@Query("SELECT * FROM myUsers WHERE id LIKE :condition")
    //fun getAllPeopleWithFavoriteColor(condition: String): List<User>

}