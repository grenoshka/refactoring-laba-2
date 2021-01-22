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
    @Query("SELECT * FROM myUsers")
    fun getAllUsers(): List<User>

    // Получение всех Object из бд с условием
    //@Query("SELECT * FROM myUsers WHERE id LIKE :condition")
    //fun getAllPeopleWithFavoriteColor(condition: String): List<User>

}